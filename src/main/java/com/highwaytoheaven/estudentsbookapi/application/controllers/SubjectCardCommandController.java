package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.SubjectCardCommandApi;
import com.highwaytoheaven.estudentsbookapi.application.services.SubjectCardService;
import com.highwaytoheaven.model.SubjectCardCreateRequestDTO;
import com.highwaytoheaven.model.SubjectCardCreateResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class SubjectCardCommandController implements SubjectCardCommandApi {

    private SubjectCardService subjectCardService;

    @Override
    public ResponseEntity<SubjectCardCreateResponseDTO> createSubjectCardForStudent(
            @Valid SubjectCardCreateRequestDTO subjectCardCreateRequestDTO)
    {
        try {
            return ResponseEntity.ok().body(subjectCardService.createNewSubjectCardForStudent(subjectCardCreateRequestDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
