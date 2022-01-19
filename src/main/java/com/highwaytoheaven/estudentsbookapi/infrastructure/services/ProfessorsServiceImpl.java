package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.ProfessorsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Subject;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SubjectCardRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SubjectRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.ProfessorDTO;
import com.highwaytoheaven.model.SubjectResponseDTO;
import com.highwaytoheaven.model.UserContactDTO;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfessorsServiceImpl extends BasicUsersService implements ProfessorsService {

    private final UserRepository userRepository;
    private final ProfessorMapper professorMapper;
    private final UserMapper userMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final SubjectMapper subjectMapper;
    private final StudentMapper studentMapper;
    private final SubjectCardRepository subjectCardRepository;
    private final SubjectRepository subjectRepository;

    public ProfessorsServiceImpl(UserRepository userRepository, ProfessorMapper professorMapper,
                                 UserMapper userMapper, ContactDetailsMapper contactDetailsMapper,
                                 SubjectMapper subjectMapper, StudentMapper studentMapper,
                                 SubjectCardRepository subjectCardRepository, SubjectRepository subjectRepository)
    {
        super(userRepository);
        this.userRepository = userRepository;
        this.professorMapper = professorMapper;
        this.userMapper = userMapper;
        this.contactDetailsMapper = contactDetailsMapper;
        this.subjectMapper = subjectMapper;
        this.studentMapper = studentMapper;
        this.subjectCardRepository = subjectCardRepository;
        this.subjectRepository = subjectRepository;
    }


    @Override
    public ProfessorDTO getProfessorDataById() {

        User professor = getUserFromContext();

        return  professorMapper.userToProfessorDTO(professor,
                        userMapper.userEntityToUserDto(
                                professor,
                                this.getProfessorContactDetails(professor)),
                        this.getListOfSubjectsWithStudents(professor)
        );
    }

    private UserContactDTO getProfessorContactDetails(User professor) {
        return contactDetailsMapper.contactDetailsToUserContactDto(professor.getContactDetails());
    }

    private List<SubjectResponseDTO> getListOfSubjectsWithStudents(User professor) {

        List<Subject> subjects = subjectRepository.getAllByUser(professor);

        return subjects.stream().map(subject -> subjectMapper.subjectToSubjectResponseDTO(subject,
                subjectCardRepository.getAllBySubjectIdAndSemesterDate(subject.getId(),
                            new Date(System.currentTimeMillis()))
                        .stream().map(sc -> studentMapper.userToStudent1DTO(sc.getUser()))
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
