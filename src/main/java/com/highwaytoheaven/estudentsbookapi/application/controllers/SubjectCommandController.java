package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.SubjectCommandApi;
import com.highwaytoheaven.model.SubjectCreateRequestDTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class SubjectCommandController implements SubjectCommandApi {

    @Override
    public ResponseEntity<SubjectDTO> createSubject(@Valid SubjectCreateRequestDTO subjectCreateRequestDTO) {
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<SubjectDTO> updateSubjectById(UUID uuid, @Valid SubjectUpdateRequestDTO subjectUpdateRequestDTO) {
        return ResponseEntity.notFound().build();
    }
}
