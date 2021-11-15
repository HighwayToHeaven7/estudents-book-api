package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsCommandApi;
import com.highwaytoheaven.model.GradeCreateRequestDTO;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeUpdateRequestDTO;
import com.sun.xml.bind.v2.TODO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.UUID;

public class StudentsCommandController implements StudentsCommandApi {


    @Override
    public ResponseEntity<GradeDTO> giveGradeToStudent(@Valid GradeCreateRequestDTO gradeCreateRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<GradeDTO> updateStudentGrade(UUID uuid, UUID uuid1, UUID uuid2, @Valid GradeUpdateRequestDTO gradeUpdateRequestDTO) {
        return null;
    }
}
