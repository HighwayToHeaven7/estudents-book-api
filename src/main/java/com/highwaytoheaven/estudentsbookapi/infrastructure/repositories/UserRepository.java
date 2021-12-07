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

    List<User> getAllById(UUID uuid);
    Optional<User> getUserById(UUID uuid);
    Optional<User> findUserByEmail(String email);
}
