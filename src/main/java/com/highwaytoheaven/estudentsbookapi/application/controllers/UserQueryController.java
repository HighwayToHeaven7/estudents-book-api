package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.UserQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.UserService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.services.UserServiceImpl;
import com.highwaytoheaven.model.UserDTO;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserQueryController implements UserQueryApi {

  private final UserService userService;

  public UserQueryController(UserServiceImpl userServiceImpl) {
    this.userService = userServiceImpl;
  }

  @Override
  public ResponseEntity<List<UserDTO>> getNewUsers() {
    return null;
  }
}
