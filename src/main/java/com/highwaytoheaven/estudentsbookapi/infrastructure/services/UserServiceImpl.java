package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.UserService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.ContactDetails;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.Role;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.ContactDetailsMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.UserMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.ContactDetailsRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.UserCreateRequestDTO;
import com.highwaytoheaven.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ContactDetailsRepository contactDetailsRepository;
    private final ContactDetailsMapper contactDetailsMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getListOfStudents() {
        List<User> students = userRepository.getUsersByRole(Role.STUDENT);

        return students.stream().map(x -> userMapper.userEntityToUserDto(x,
                contactDetailsMapper.contactDetailsToUserContactDto(x.getContactDetails())))
                .limit(50).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getListOfProfessors() {
        List<User> professors = userRepository.getUsersByRole(Role.PROFESSOR);

        return professors.stream().map(x -> userMapper.userEntityToUserDto(x,
                        contactDetailsMapper.contactDetailsToUserContactDto(x.getContactDetails())))
                .limit(50).collect(Collectors.toList());
    }


    @Override
    public UserDTO createNewUser(UserCreateRequestDTO userDTO) {

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPhoneNumber(userDTO.getPhoneNumber());
        contactDetailsRepository.save(contactDetails);

        User user = User.builder().name(userDTO.getName()).surname(userDTO.getSurname())
                                .accountStatus(UserStatus.valueOf(userDTO.getAccountStatus()))
                                .email(userDTO.getEmail()).role(Role.valueOf(userDTO.getRole()))
                                .password(passwordEncoder.encode(userDTO.getPassword()))
                                .contactDetails(contactDetails).build();

        userRepository.save(user);

        return userMapper.userEntityToUserDto(user, contactDetailsMapper.contactDetailsToUserContactDto(contactDetails));
    }

}
