package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters.GradeConverter;
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
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final SemesterRepository semesterRepository;
    private final SubjectCardRepository subjectCardRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectCardMapper subjectCardMapper;
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final GradeConverter converter;

    @Override
    public List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents() {
       List<Semester> semestersWithGroupNames = semesterRepository.getAllBySemesterDate(
                                                    new Date(System.currentTimeMillis()));

        return getAllSemestersWithStudentDTO(semestersWithGroupNames);
    }

    @Override
    public List<StudentDTO> getStudentById(UUID studentUuid) {
        return userRepository.getAllById(studentUuid).stream().map(user -> studentMapper.userToStudentDTO(
                user, userMapper.userEntityToUserDto(user,
                        contactDetailsMapper.contactDetailsToUserContactDto(user.getContactDetails())),
                getAllSubjectsCardsByUserAndMapToSubjectCardDTO(user)
                )).collect(Collectors.toList());
    }

    @Override
    public List<StudentSubjectCardResponseDTO> getStudentCardByStudentIdAndSemester(UUID studentUuid,
                                                    Optional<Integer> semesterNumber) throws Exception {

        Optional<User> student = userRepository.getUserById(studentUuid);

        if (student.isEmpty())
            throw new Exception("There's no user with this id!");

        if (semesterNumber.isEmpty())
            throw new IllegalArgumentException("Wrong semester number!");

        Optional<Semester> semester = semesterRepository.getSemesterBySemesterNumberAndUsersListContaining(
                                                            semesterNumber.get(), student.get());

        if (semester.isEmpty())
            throw new Exception("Semester not exists!");

        List<SubjectCard> subjectCards = subjectCardRepository.getAllByUserAndSemester(student.get(), semester.get());

        return  subjectCards.stream().map(subjectCard -> subjectCardMapper.subjectCardToStudentSubjectCardResponseDTO(
                    subjectCard, this.getAllGradesBySubjectCardAndMapToListOfGradeDTO(subjectCard)
                )).collect(Collectors.toList());
    }

    @Override
    public List<StudentSubjectCardResponseDTO> getSubjectCardDetailsByStudentIdAndSubjectCardId(UUID studentUuid,
                                                                                                UUID subjectUuid) {

        Optional<User> student = userRepository.getUserById(studentUuid);

        if (student.isEmpty())
            throw new IllegalArgumentException("Student with this id does not exist!");

        Optional<Subject> subject = subjectRepository.getSubjectById(subjectUuid);

        if (subject.isEmpty())
            throw new IllegalArgumentException("Subject with this id does not exist!");

        List<SubjectCard> subjectCards = subjectCardRepository.getAllByUserAndSubject(student.get(), subject.get());

        return subjectCards.stream().map(subjectCard -> subjectCardMapper.subjectCardToStudentSubjectCardResponseDTO(
                subjectCard, this.getAllGradesBySubjectCardAndMapToListOfGradeDTO(subjectCard)
        )).collect(Collectors.toList());
    }


    @Override
    public Optional<GradeDTO> updateStudentGrade(UUID studentUuid, UUID subjectCardUuid, UUID gradeUuid,
                                     GradeUpdateRequestDTO gradeDTO) {

        if (userRepository.findById(studentUuid).isEmpty())
            throw new IllegalArgumentException("Student with this id does not exist!");

        if (subjectCardRepository.findById(subjectCardUuid).isEmpty())
            throw new IllegalArgumentException("SubjectCard with this id does not exist!");

        Optional<Grade> gradeOptional = gradeRepository.findById(gradeUuid);

        if(gradeOptional.isEmpty())
            throw new IllegalArgumentException("Grade with this id does not exist!");

        Grade grade = gradeOptional.get();
        grade.setValue(converter.convertValueToType(gradeDTO.getGrade(), GradeType.valueOf(gradeDTO.getGradeType()))
                        .get());
        grade.setGradeType(GradeType.valueOf(gradeDTO.getGradeType()));
        grade.setWeight(Double.valueOf(gradeDTO.getGradeWeight())); //TODO
        grade.setDescription(gradeDTO.getGradeDescription());

        gradeRepository.save(grade);

        return Optional.of(gradeMapper.gradeToGradeDTO(grade));
    }

    @Override
    public GradeDTO createNewGrade(GradeCreateRequestDTO gradeCreateRequestDTO) throws Exception {

        Optional<User> user = userRepository.getUserById(gradeCreateRequestDTO.getStudentUuid());

        if(user.isEmpty())
            throw new IllegalArgumentException();

        Optional<Subject> subject = subjectRepository.getSubjectById(gradeCreateRequestDTO.getSubjectUuid());

        if (subject.isEmpty())
            throw new IllegalArgumentException();

        Optional<SubjectCard> subjectCard = subjectCardRepository.getSubjectCardByUserAndSubject(user.get(), subject.get());

        if(subjectCard.isEmpty())
            throw new Exception();

        Grade newGrade = Grade.builder().value(converter.convertValueToType(gradeCreateRequestDTO.getGrade(),
                                                GradeType.valueOf(gradeCreateRequestDTO.getGradeType())).get())
                                        .gradeType(GradeType.valueOf(gradeCreateRequestDTO.getGradeType()))
                                        .weight(Double.valueOf(gradeCreateRequestDTO.getGradeWeight()))
                                        .description(gradeCreateRequestDTO.getGradeDescription())
                                        .subjectCard(subjectCard.get()).build();

        return gradeMapper.gradeToGradeDTO(newGrade);
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
