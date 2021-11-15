package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.ProfessorQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.ProfessorsService;
import com.highwaytoheaven.model.ProfessorDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@AllArgsConstructor
public class ProfessorQueryController implements ProfessorQueryApi {

    private final ProfessorsService professorsService;

    @Override
    public ResponseEntity<ProfessorDTO> getProfessorDataById(UUID uuid) {
        try {
            return ResponseEntity.ok().body(professorsService.getProfessorDataById(uuid));
        }catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}
