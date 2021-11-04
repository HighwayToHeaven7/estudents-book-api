package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeUpdateRequestDTO;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentsService {

    List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents() throws Exception;
    List<StudentDTO> getStudentById(UUID uuid);
    List<GradeDTO> getStudentGradesByStudentId(UUID uuid, @Valid Optional<Integer> optional);
    GradeDTO giveStudentGrade(UUID uuid, UUID uuid1, @Valid GradeUpdateRequestDTO gradeUpdateRequestDTO);
}
