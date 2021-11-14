package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.SubjectCard;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.GradeRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SemesterRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SubjectCardRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentsServiceImpl implements StudentsService {

    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectCardRepository subjectCardRepository;
    private final SubjectCardMapper subjectCardMapper;
    private final StudentMapper studentMapper;
    private final UserMapper userMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    @Override
    public List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents() {
       List<Semester> semestersWithGroupNames = semesterRepository.getAllBySemesterDate(
                                                    new Date(System.currentTimeMillis()));

        return getAllSemestersWithStudentDTO(semestersWithGroupNames);
    }

    @Override
    public List<StudentDTO> getStudentById(UUID uuid) {
//        return userRepository.getAllById(uuid).stream().map(studentMapper::userToStudentDTO).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<GradeDTO> getStudentGradesByStudentId(UUID uuid, Optional<Integer> optional) {
        return null;
    }

    @Override
    public GradeDTO giveStudentGrade(UUID uuid, UUID uuid1, GradeUpdateRequestDTO gradeUpdateRequestDTO) {
        return null;
    }

    private List<GroupWithStudentsResponseDTO> getAllSemestersWithStudentDTO(List<Semester> semestersWithGroupNames) {
        return semestersWithGroupNames.stream().map(semester ->
            studentMapper.semesterToGroupWithStudentsResponseDTO(
                    semester, this.getListOfStudentDTOByNewSemester(semester))
        ).collect(Collectors.toList());
    }

    private List<StudentDTO> getListOfStudentDTOByNewSemester(Semester semester) {
        return semester.getUsersList().stream().map(user -> {
            UserContactDTO contactDTO = contactDetailsMapper.contactDetailsToUserContactDto(user.getContactDetails());

            return studentMapper.userToStudentDTO(user,
                    userMapper.userEntityToUserDto(user, contactDTO),
                    this.getAllSubjectsCardsByUserAndMapToSubjectCardDTO(user));
        }).collect(Collectors.toList());
    }

    private List<StudentSubjectCardResponseDTO> getAllSubjectsCardsByUserAndMapToSubjectCardDTO(User user) {
        return subjectCardRepository.getAllByUser(user).stream().map(subjectCard ->
                    subjectCardMapper.subjectCardToStudentSubjectCardResponseDTO(subjectCard,
                            this.getAllGradesBySubjectCardAndMapToListOfGradeDTO(subjectCard))
                ).collect(Collectors.toList());
    }

    private List<GradeDTO> getAllGradesBySubjectCardAndMapToListOfGradeDTO(SubjectCard subjectCard) {
        return  gradeRepository.getAllBySubjectCard(subjectCard).stream().map(gradeMapper::gradeToGradeDTO)
                .collect(Collectors.toList());
    }
}
