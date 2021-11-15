package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.StudentsQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import com.highwaytoheaven.model.StudentSubjectCardResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class StudentsQueryController implements StudentsQueryApi {

    private final StudentsService studentsServiceImpl;

    @Override
    public ResponseEntity<List<GroupWithStudentsResponseDTO>> getListOfGroupsWithStudents() {
        return ResponseEntity.ok().body(studentsServiceImpl.getListOfGroupsWithStudents());
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getStudentById(UUID uuid) {
        return ResponseEntity.ok().body(studentsServiceImpl.getStudentById(uuid));
    }

    @Override
    public ResponseEntity<List<StudentSubjectCardResponseDTO>> getStudentCardByStudentIdAndSemester(UUID uuid,
                                                               @Valid Optional<Integer> optional) {
        return null;
    }

    @Override
    public ResponseEntity<List<StudentSubjectCardResponseDTO>> getSubjectCardDetailsByStudentIdAndSubjectCardId(
                                                                UUID uuid, UUID uuid1) {
        return null;
    }
}
