package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, UUID> {
}
