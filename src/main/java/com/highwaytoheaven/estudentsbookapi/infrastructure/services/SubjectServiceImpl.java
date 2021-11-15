package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.SubjectService;
import com.highwaytoheaven.model.SubjectCreateRequestDTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectUpdateRequestDTO;

import java.util.UUID;

public class SubjectServiceImpl implements SubjectService {
    @Override
    public SubjectDTO createSubject(SubjectCreateRequestDTO subjectCreateRequestDTO) {
        //TODO -> remove
        return null;
    }

    @Override
    public SubjectDTO updateSubjectById(UUID subjectUuid, SubjectUpdateRequestDTO subjectUpdateRequestDTO) {
        //TODO -> remove
        return null;
    }
}
