package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.*;

import java.util.List;
import java.util.UUID;

public interface StudentsService {
    List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents();
    StudentDTO getStudent();
    List<StudentSubjectCardResponseDTO> getStudentSubjectCards(Integer semester) throws Exception;
    List<StudentSubjectCardResponseDTO> getStudentSubjectCards(UUID student, UUID subjectUuid);
    GradeDTO updateStudentGrade(UUID gradeUUID, GradeRequestDTO gradeDTO) throws Exception;
    GradeDTO createNewGrade(GradeRequestDTO gradeCreateRequestDTO) throws Exception;
}
