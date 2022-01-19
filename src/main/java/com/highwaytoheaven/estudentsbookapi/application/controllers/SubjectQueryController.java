package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.SubjectQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.SubjectService;
import com.highwaytoheaven.model.SubjectDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class SubjectQueryController implements SubjectQueryApi {

    private final SubjectService subjectService;

    @Override
    public ResponseEntity<List<SubjectDetailsDTO>> getAllSubjects() {
        return ResponseEntity.ok().body(subjectService.getListOfSubjects());
    }
}
