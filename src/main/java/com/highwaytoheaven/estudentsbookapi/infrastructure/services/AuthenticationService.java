package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.google.gson.Gson;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.UserMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.UserAuthPostResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public String createAuthDtoAndConvertToJson(String email, String token) throws IOException {

        Optional<User> userOptional = userRepository.findUserByEmail(email);

        if (userOptional.isEmpty())
            throw new IOException();

        UUID uuid = userOptional.get().getId();

        UserAuthPostResponseDTO userDTO = userMapper.userDetailsToUserAuthPostResponseDTO(uuid, email, token);

        return new Gson().toJson(userDTO);
    }
}
