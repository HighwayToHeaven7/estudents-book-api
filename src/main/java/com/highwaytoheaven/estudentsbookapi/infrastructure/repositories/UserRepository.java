package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.accountStatus = 'NEW'")
    List<User> getUsersByAccountStatusIsNew();

    //TODO -> add major as parameter : ?
//    @Query("SELECT u FROM User u WHERE u.accountStatus = 'ACTIVE' AND u.role = 'STUDENT'")
//    List<User> getAllActiveStudents();

    List<User> getAllById(UUID uuid);
    Optional<User> getUserById(UUID uuid);
}
