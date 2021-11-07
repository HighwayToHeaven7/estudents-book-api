package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class StudentsQueryController implements StudentsQueryApi {

    private final StudentsService studentsService;


    @Override
    public ResponseEntity<List<GroupWithStudentsResponseDTO>> getListOfGrupsWithStudents() {
        return ResponseEntity.ok()
                .body(studentsService.getListOfGroupsWithStudents());
    }


    //TODO getStudentById() -> return StudentDTO
    @Override
    public ResponseEntity<List<StudentDTO>> getStudentById(UUID uuid) {
        return ResponseEntity.ok()
                .body(studentsService.getStudentById(uuid));
    }

    @Override
    public ResponseEntity<List<GradeDTO>> getStudentGradesByStudentId(UUID uuid, @Valid Optional<Integer> optional) {
        return null;
    }
}
