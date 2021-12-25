package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.ProfessorsQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.ProfessorsService;
import com.highwaytoheaven.model.ProfessorDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProfessorQueryController implements ProfessorsQueryApi {

    private final ProfessorsService professorsService;

    @Override
    public ResponseEntity<ProfessorDTO> getProfessorDataById() {
        try {
            return ResponseEntity.ok().body(professorsService.getProfessorDataById());
        }catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}
