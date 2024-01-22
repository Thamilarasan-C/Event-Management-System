package com.thamil.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Registration;
import com.thamil.project.service.RegistrationService;


@RestController
@RequestMapping(path = "registration")
public class RegistrationController {
  
  @Autowired
  private RegistrationService service;

  @PostMapping("addRegistration")
  public ResponseEntity<Registration> insertRegistration(@RequestBody Registration registration) throws CustomException {
    Registration r = service.saveRegistration(registration);
    return new ResponseEntity<Registration>(r,HttpStatus.OK);
  }
  
  @GetMapping("/getRegistrations/user/{userId}")
  public ResponseEntity<List<Registration>> selectEventsByOrganizer(@PathVariable Long userId) {
    List<Registration> registrations = service.getRegistrationsByUserId(userId);
    if (registrations == null) {
      return new ResponseEntity<>(new ArrayList<Registration>(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(registrations, HttpStatus.OK);
  }

}