package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsCommandApi;
import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class StudentsCommandController implements StudentsCommandApi {

    private final StudentsService studentsService;

    @Override
    public ResponseEntity<GradeDTO> giveGradeToStudent(@Valid GradeRequestDTO gradeRequestDTO) {
        try {
            return ResponseEntity.ok().body(studentsService.createNewGrade(gradeRequestDTO));
        } catch (IllegalArgumentException ie){
            ie.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<GradeDTO> updateStudentGrade(UUID gradeUUID, @Valid GradeRequestDTO gradeUpdateRequestDTO) {
        try {
            return ResponseEntity.ok().body(studentsService.updateStudentGrade(gradeUUID, gradeUpdateRequestDTO));
        } catch (IllegalArgumentException ie){
            ie.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
