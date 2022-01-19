package com.highwaytoheaven.estudentsbookapi.application.services;

import com.highwaytoheaven.model.UserCreateRequestDTO;
import com.highwaytoheaven.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getListOfStudents();
    List<UserDTO> getListOfProfessors();
    UserDTO createNewUser(UserCreateRequestDTO userCreateRequestDTO);
}
