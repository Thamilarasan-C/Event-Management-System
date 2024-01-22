package com.thamil.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.dto.TicketRegistration;
import com.thamil.project.dto.TicketToken;
import com.thamil.project.exception.CustomException;
import com.thamil.project.service.TicketRegistrationService;

@RestController
@RequestMapping("ticketRegistration")
public class TicketRegistrationController {

  @Autowired
  private TicketRegistrationService service;

  @PostMapping("/saveRegistration")
  public ResponseEntity<List<TicketToken>> insertUser(@RequestBody TicketRegistration ticketRegistration) throws CustomException{
    return new ResponseEntity<List<TicketToken>>(service.saveTicketRegistration(ticketRegistration), HttpStatus.OK);
  }
}
