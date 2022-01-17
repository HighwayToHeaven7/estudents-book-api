package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.SubjectService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Subject;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.SubjectMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SubjectRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.SubjectCreateRequestDTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectDetailsDTO;
import com.highwaytoheaven.model.SubjectPatchRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepo;
    private final SubjectMapper subjectMapper;
    private final UserRepository userRepo;

    @Override
    public SubjectDTO createSubject(SubjectCreateRequestDTO dto) throws Exception {
        Optional<User> professor = userRepo.findById(dto.getProfessorUuid());

        if (professor.isEmpty())
            throw new Exception("Not found professor with this UUID!");

        Subject subject = Subject.builder().name(dto.getSubjectName())
                                    .user(professor.get()).build();

        subjectRepo.save(subject);

        return subjectMapper.subjectToSubjectDTO(subject);
    }

    @Override
    public SubjectDTO setNewProfessorToSubject(UUID uuid, SubjectPatchRequestDTO dto) throws Exception {
        Optional<Subject> subjectOptional = subjectRepo.getSubjectById(uuid);

        if (subjectOptional.isEmpty())
            throw new IllegalArgumentException("Not found subject with this UUID!");

        Optional<User> professorOptional = userRepo.findById(dto.getProfessorUuid());

        if (professorOptional.isEmpty())
            throw new Exception("Not found professor with this UUID!");


        Subject subject = subjectOptional.get();
        subject.setUser(professorOptional.get());

        subjectRepo.save(subject);

        return subjectMapper.subjectToSubjectDTO(subject);
    }

    @Override
    public List<SubjectDetailsDTO> getListOfSubjects() {
        return subjectRepo.findAll().stream().map(subjectMapper::subjectToSubjectDetailsDTO)
                .collect(Collectors.toList());
    }
}
