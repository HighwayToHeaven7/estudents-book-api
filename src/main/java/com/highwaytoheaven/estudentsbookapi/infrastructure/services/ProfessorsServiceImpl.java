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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProfessorsServiceImpl implements ProfessorsService {

    private final UserRepository userRepository;
    private final ProfessorMapper professorMapper;
    private final UserMapper userMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final SubjectMapper subjectMapper;
    private final StudentMapper studentMapper;
    private final SubjectCardRepository subjectCardRepository;
    private final SubjectRepository subjectRepository;


    @Override
    public ProfessorDTO getProfessorDataById(UUID professorUuid) {
        Optional<User> professor = userRepository.getUserById(professorUuid);

        if(professor.isEmpty())
            throw new IllegalArgumentException("There's no professor with this id!");

        return  professorMapper.userToProfessorDTO(professor.get(),
                        userMapper.userEntityToUserDto(
                                professor.get(),
                                this.getProfessorContactDetails(professor.get())),
                        this.getListOfSubjectsWithStudents(professor.get())
        );
    }

    private UserContactDTO getProfessorContactDetails(User professor) {
        return contactDetailsMapper.contactDetailsToUserContactDto(professor.getContactDetails());
    }

    private List<SubjectResponseDTO> getListOfSubjectsWithStudents(User professor) {
        List<Subject> subjects = subjectRepository.getAllByUser(professor);

        return subjects.stream().map(subject -> {
            return subjectMapper.subjectToSubjectResponseDTO(subject,
                    subjectCardRepository.getAllBySubjectIdAndSemesterDate(subject.getId(),
                            new Date(System.currentTimeMillis())).stream().map(sc ->  {
                                return studentMapper.userToStudent1DTO(sc.getUser());
                    }).collect(Collectors.toList())
            );
        }).collect(Collectors.toList());
    }
}
