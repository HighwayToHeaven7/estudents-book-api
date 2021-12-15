package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import com.highwaytoheaven.model.StudentSubjectCardResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class StudentsQueryController implements StudentsQueryApi {

    private final StudentsService studentsServiceImpl;

    @Override
    public ResponseEntity<List<GroupWithStudentsResponseDTO>> getGroupsWithStudents() {
        return ResponseEntity.ok().body(studentsServiceImpl.getListOfGroupsWithStudents());
    }

    @Override
    public ResponseEntity<StudentDTO> getStudentById(UUID uuid) {
        return ResponseEntity.ok().body(studentsServiceImpl.getStudentById(uuid));
    }

    @Override
    public ResponseEntity<List<StudentSubjectCardResponseDTO>> getStudentCardBySemester(UUID uuid, Integer semester) {
        try {
            return ResponseEntity.ok().body(studentsServiceImpl.getStudentSubjectCards(uuid, semester));
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<StudentSubjectCardResponseDTO>> getSubjectCardBySubjectId(UUID uuid, UUID uuid1) {
        try {
            return ResponseEntity.ok().body(studentsServiceImpl.getStudentSubjectCards(uuid, uuid1));
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
