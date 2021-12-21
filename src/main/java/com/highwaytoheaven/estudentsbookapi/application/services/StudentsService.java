package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.*;

import java.util.List;
import java.util.UUID;

public interface StudentsService {
    List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents();
    StudentDTO getStudentById();
    List<StudentSubjectCardResponseDTO> getStudentSubjectCards(Integer semester) throws Exception;
    List<StudentSubjectCardResponseDTO> getStudentSubjectCards(UUID subjectUuid);
    GradeDTO updateStudentGrade(UUID studentUuid, UUID subjectCardUuid, UUID gradeUuid, GradeUpdateRequestDTO gradeDTO)
            throws Exception;
    GradeDTO createNewGrade(GradeCreateRequestDTO gradeCreateRequestDTO) throws Exception;
}
