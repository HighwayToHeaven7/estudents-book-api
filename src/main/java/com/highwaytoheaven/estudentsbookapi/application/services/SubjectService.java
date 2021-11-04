package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.SubjectCreateRequestDTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectUpdateRequestDTO;

import javax.validation.Valid;
import java.util.UUID;

public interface SubjectService {

    SubjectDTO createSubject(@Valid SubjectCreateRequestDTO subjectCreateRequestDTO);
    SubjectDTO updateSubjectById(UUID uuid, @Valid SubjectUpdateRequestDTO subjectUpdateRequestDTO);

}
