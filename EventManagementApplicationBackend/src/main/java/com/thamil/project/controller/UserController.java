package com.thamil.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.dto.LoginRequest;
import com.thamil.project.dto.LoginResponse;
import com.thamil.project.dto.SignUpRequest;
import com.thamil.project.exception.CustomException;
import com.thamil.project.service.UserService;

@RestController
@RequestMapping(path = "user")
@CrossOrigin("*")
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping("/signUp")
  public ResponseEntity<LoginResponse> insertUser(@RequestBody SignUpRequest signUpRequest) throws CustomException {
    return new ResponseEntity<LoginResponse>(service.saveUser(signUpRequest), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws CustomException {
    return new ResponseEntity<LoginResponse>(service.validateUser(loginRequest),HttpStatus.OK);
}
}