package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.ProfessorDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ProfessorsService {
    ProfessorDTO getProfessorDataById(UUID uuid);
}
