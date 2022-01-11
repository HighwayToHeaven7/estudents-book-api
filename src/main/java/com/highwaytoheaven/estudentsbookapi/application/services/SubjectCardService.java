package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.SubjectCardCreateRequestDTO;
import com.highwaytoheaven.model.SubjectCardCreateResponseDTO;

public interface SubjectCardService {
    SubjectCardCreateResponseDTO createNewSubjectCardForStudent(SubjectCardCreateRequestDTO subjectCardDTO) throws Exception;
}
