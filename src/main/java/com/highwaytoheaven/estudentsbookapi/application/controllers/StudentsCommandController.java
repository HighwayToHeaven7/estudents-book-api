package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsCommandApi;
import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.model.GradeCreateRequestDTO;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeUpdateRequestDTO;
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
    public ResponseEntity<GradeDTO> giveGradeToStudent(@Valid GradeCreateRequestDTO gradeCreateRequestDTO) {
        try {
            return ResponseEntity.ok().body(studentsService.createNewGrade(gradeCreateRequestDTO));
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
    public ResponseEntity<GradeDTO> updateStudentGrade(UUID uuid, UUID uuid1, UUID uuid2,
                                                       @Valid GradeUpdateRequestDTO gradeUpdateRequestDTO) {
        try {
            return ResponseEntity.ok().body(studentsService.updateStudentGrade(uuid, uuid1, uuid2,
                                                                                gradeUpdateRequestDTO));
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
