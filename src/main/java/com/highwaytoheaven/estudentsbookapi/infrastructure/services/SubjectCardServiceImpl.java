package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.SubjectCardService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.SubjectCardMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.*;
import com.highwaytoheaven.model.SubjectCardCreateRequestDTO;
import com.highwaytoheaven.model.SubjectCardCreateResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubjectCardServiceImpl implements SubjectCardService {

    private final SubjectCardRepository subjectCardRepo;
    private final UserRepository userRepo;
    private final SubjectRepository subjectRepo;
    private final SemesterRepository semesterRepo;
    private final SemesterService semesterService;
    private final MajorRepository majorRepository;
    private final SubjectCardMapper subjectCardMapper;


    @Override
    @Transactional
    public SubjectCardCreateResponseDTO createNewSubjectCardForStudent(SubjectCardCreateRequestDTO dto) throws Exception {
        Optional<User> studentOptional = userRepo.findById(dto.getStudentUuid());

        if(studentOptional.isEmpty())
            throw new Exception("User not exist!");

        User student = studentOptional.get();

        Optional<Subject> subjectOptional = subjectRepo.getSubjectById(dto.getSubjectUuid());

        if(subjectOptional.isEmpty())
            throw new Exception("Subject not exist!");

        Subject subject = subjectOptional.get();

        if(subjectCardRepo.existsBySubjectAndUser(subject, student))
            throw new Exception("Subject-card already exists!");

        Optional<Major> majorOptional = majorRepository.getMajorBySubjectsListContaining(subject);

        if(majorOptional.isEmpty())
            throw new Exception("Subject is not assigned to a major!");

        Major major = majorOptional.get();

        Optional<Semester> semesterOptional = semesterRepo.getSemesterByDateAndStudent(
                                        new Date(System.currentTimeMillis()), student);

        Semester semester;

        if(semesterOptional.isEmpty()) {
            semester = semesterService.createNewSemesterByDateAndStudentUUID(student, dto.getGroupName(), major);
        } else {
            semester = semesterOptional.get();
        }

        SubjectCard subjectCard = SubjectCard.builder().subject(subject).user(student).semester(semester)
                                            .expectedFinalGrade(0.0).build();

        subjectCardRepo.save(subjectCard);

        return subjectCardMapper.subjectCardToSubjectCardCreateResponseDTO(subjectCard);
    }
}
