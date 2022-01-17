package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.SubjectCreateRequestDTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectDetailsDTO;
import com.highwaytoheaven.model.SubjectPatchRequestDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface SubjectService {

    SubjectDTO createSubject(@Valid SubjectCreateRequestDTO subjectCreateRequestDTO) throws Exception;
    SubjectDTO setNewProfessorToSubject(UUID uuid, @Valid SubjectPatchRequestDTO subjectPatchRequestDTO) throws Exception;
    List<SubjectDetailsDTO> getListOfSubjects();
}
