package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.ProfessorQueryApi;
import com.highwaytoheaven.model.ProfessorDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class ProfessorQueryController implements ProfessorQueryApi {
    @Override
    public ResponseEntity<ProfessorDTO> getProfessorDataById(UUID uuid) {
        return null;
    }
}
