package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.SubjectCommandApi;
import com.highwaytoheaven.estudentsbookapi.application.services.SubjectService;
import com.highwaytoheaven.model.SubjectCreateRequestDTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectPatchRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class SubjectCommandController implements SubjectCommandApi {

    private final SubjectService subjectService;

    @Override
    public ResponseEntity<SubjectDTO> createSubject(@Valid SubjectCreateRequestDTO subjectCreateRequestDTO) {
        try {
            return ResponseEntity.ok().body(subjectService.createSubject(subjectCreateRequestDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<SubjectDTO> setNewProfessorToSubject(UUID uuid, @Valid SubjectPatchRequestDTO subjectPatchRequestDTO) {
        try {
            return ResponseEntity.ok().body(subjectService.setNewProfessorToSubject(uuid, subjectPatchRequestDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
