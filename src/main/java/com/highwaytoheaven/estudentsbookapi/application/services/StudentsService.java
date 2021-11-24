package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentsService {

    List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents();

    List<StudentDTO> getStudentById(UUID studentUuid);

    List<StudentSubjectCardResponseDTO> getStudentCardByStudentIdAndSemester(UUID studentUuid,
                                            Integer semesterNumber) throws Exception;

    List<StudentSubjectCardResponseDTO> getSubjectCardDetailsByStudentIdAndSubjectCardId(UUID studentUuid,
                                                                                         UUID subjectUuid);

    GradeDTO updateStudentGrade(UUID studentUuid, UUID subjectCardUuid, UUID gradeUuid, GradeUpdateRequestDTO gradeDTO);

    GradeDTO createNewGrade(GradeCreateRequestDTO gradeCreateRequestDTO) throws Exception;
}
