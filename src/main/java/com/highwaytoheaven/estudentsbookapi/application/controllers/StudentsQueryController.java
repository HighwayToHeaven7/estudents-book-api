package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsQueryApi;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentsQueryController implements StudentsQueryApi {

    @Override
    public ResponseEntity<List<GroupWithStudentsResponseDTO>> getListOfGrupsWithStudents() {
        return null;
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getStudentById(UUID uuid) {
        return null;
    }

    @Override
    public ResponseEntity<List<GradeDTO>> getStudentGradesByStudentId(UUID uuid, @Valid Optional<Integer> optional) {
        return null;
    }
}
