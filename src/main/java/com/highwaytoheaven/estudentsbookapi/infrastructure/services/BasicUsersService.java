package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class BasicUsersService {

    private final UserRepository userRepository;

    public User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String emailFromContext = authentication.getName();
        UUID uuidFromContext = UUID.fromString(authentication.getDetails().toString());

        Optional<User> user = userRepository.findById(uuidFromContext);
        Optional<User> userByEmail = userRepository.findUserByEmail(emailFromContext);

        if (user.isEmpty() || userByEmail.isEmpty())
            throw new UsernameNotFoundException("User not found in context!");

        if (!user.get().equals(userByEmail.get()))
            throw new UsernameNotFoundException("User not found in context!");

        return user.get();
    }
}
