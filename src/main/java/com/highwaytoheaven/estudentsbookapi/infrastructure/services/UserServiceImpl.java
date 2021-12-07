package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.UserService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.ContactDetailsMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.UserMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ContactDetailsMapper contactDetailsMapper;

    @Override
    public List<UserDTO> getNewUsers() {
        List<User> newUsers = userRepository.getUsersByAccountStatusIsNew();

        return newUsers.stream().map(x -> userMapper.userEntityToUserDto(x,
                contactDetailsMapper.contactDetailsToUserContactDto(x.getContactDetails())))
                .collect(Collectors.toList());
    }


}
