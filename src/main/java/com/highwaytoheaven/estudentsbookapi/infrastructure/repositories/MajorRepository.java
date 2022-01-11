package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Major;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MajorRepository extends JpaRepository<Major, UUID> {
    Optional<Major> getMajorBySubjectsListContaining(Subject subject);
}
