package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.UserCommandApi;
import com.highwaytoheaven.model.UserAuthPostRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserCommandController implements UserCommandApi {

    @Override
    public ResponseEntity<Object> getNewToken(@Valid UserAuthPostRequestDTO userAuthPostRequestDTO) {
        return ResponseEntity.ok().body("");
    }
}
