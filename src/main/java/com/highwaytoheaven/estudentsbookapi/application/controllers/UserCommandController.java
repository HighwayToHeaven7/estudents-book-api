package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.UserCommandApi;
import com.highwaytoheaven.estudentsbookapi.application.services.UserService;
import com.highwaytoheaven.model.UserAuthPostRequestDTO;
import com.highwaytoheaven.model.UserAuthPostResponseDTO;
import com.highwaytoheaven.model.UserCreateRequestDTO;
import com.highwaytoheaven.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserCommandController implements UserCommandApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> createNewUser(@Valid UserCreateRequestDTO userCreateRequestDTO) {
        try {
            return ResponseEntity.ok().body(userService.createNewUser(userCreateRequestDTO));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<UserAuthPostResponseDTO> getNewToken(@Valid UserAuthPostRequestDTO userAuthPostRequestDTO) {
        return ResponseEntity.ok().build();
    }
}
