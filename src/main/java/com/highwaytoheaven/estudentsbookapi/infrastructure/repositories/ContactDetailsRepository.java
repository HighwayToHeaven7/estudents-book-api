package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, UUID> {

}
