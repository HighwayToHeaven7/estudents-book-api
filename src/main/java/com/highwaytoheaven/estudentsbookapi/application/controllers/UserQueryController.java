package com.highwaytoheaven.estudentsbookapi.application.controllers;

import com.highwaytoheaven.api.UserQueryApi;
import com.highwaytoheaven.estudentsbookapi.application.services.UserService;
import com.highwaytoheaven.model.UserDTO;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class UserQueryController implements UserQueryApi {

  private final UserService userService;

  @Override
  public ResponseEntity<List<UserDTO>> getProfessors() {
    return ResponseEntity.ok().body(userService.getListOfProfessors());
  }

  @Override
  public ResponseEntity<List<UserDTO>> getStudents() {
    return ResponseEntity.ok().body(userService.getListOfStudents());
  }
}
