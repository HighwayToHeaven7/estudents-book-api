package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.ProfessorsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.ContactDetailsMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.ProfessorMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.UserMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.ProfessorDTO;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class ProfessorsServiceImpl implements ProfessorsService {

    private final UserRepository userRepository;
    private final ProfessorMapper professorMapper;
    private final UserMapper userMapper;
    private final ContactDetailsMapper contactDetailsMapper;

    @Override
    public ProfessorDTO getProfessorDataById(UUID professorUuid) {
        Optional<User> professor = userRepository.getUserById(professorUuid);
        if(professor.isEmpty())
            throw new IllegalArgumentException("There's no user with this id!");

        return professorMapper.userToProfessorDTO(professor.get(), userMapper.userEntityToUserDto(professor.get(),
                contactDetailsMapper.contactDetailsToUserContactDto(professor.get().getContactDetails())));
    }
}
