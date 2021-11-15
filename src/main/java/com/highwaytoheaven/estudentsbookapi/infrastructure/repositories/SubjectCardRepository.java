package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Subject;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.SubjectCard;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectCardRepository extends JpaRepository<SubjectCard, UUID> {
    List<SubjectCard> getAllByUser(User user);
    List<SubjectCard> getAllByUserAndSemester(User user, Semester semester);
    List<SubjectCard> getAllByUserAndSubject(User user, Subject subject);
    Optional<SubjectCard> getSubjectCardByUserAndSubject(User user, Subject subject);
}
