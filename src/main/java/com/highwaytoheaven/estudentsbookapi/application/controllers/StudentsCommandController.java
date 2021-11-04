package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsCommandApi;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.UUID;

public class StudentsCommandController implements StudentsCommandApi {

    @Override
    public ResponseEntity<GradeDTO> giveStudentGrade(UUID uuid, UUID uuid1, @Valid GradeUpdateRequestDTO gradeUpdateRequestDTO) {
        return null;
    }
}
