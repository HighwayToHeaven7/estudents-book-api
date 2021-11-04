package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.UserService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.ContactDetailsMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.UserMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ContactDetailsMapper contactDetailsMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           ContactDetailsMapper contactDetailsMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.contactDetailsMapper = contactDetailsMapper;
    }

    @Override
    public List<UserDTO> getNewUsers() throws Exception{
        Optional<List<User>> newUsers = userRepository.getUsersByAccountStatusIsNew();

        if (newUsers.isEmpty()) throw new Exception();

        List<User> newUsersList = newUsers.get();

        return newUsersList.stream().map(x -> userMapper.userEntityToUserDto(x,
                contactDetailsMapper.contactDetailsToUserContactDto(x.getContactDetails())))
                .collect(Collectors.toList());
    }
}
