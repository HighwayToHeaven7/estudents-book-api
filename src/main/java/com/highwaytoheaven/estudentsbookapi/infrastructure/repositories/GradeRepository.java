package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Grade;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.SubjectCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {
    List<Grade> getAllBySubjectCard(SubjectCard subjectCard);
}
