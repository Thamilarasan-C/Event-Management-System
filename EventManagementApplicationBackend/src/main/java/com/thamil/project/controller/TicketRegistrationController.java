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
import com.thamil.project.model.Ticket;
import com.thamil.project.service.TicketRegistrationService;
import com.thamil.project.service.TicketService;

@RestController
@RequestMapping("ticketRegistration")
public class TicketRegistrationController {

  @Autowired
  private TicketRegistrationService service;

  @Autowired
  private TicketService ticketService;

  @PostMapping("/saveRegistration")
  public ResponseEntity<List<TicketToken>> saveTicketRegistration(@RequestBody TicketRegistration ticketRegistration)
      throws CustomException {
    return new ResponseEntity<List<TicketToken>>(service.saveTicketRegistration(ticketRegistration), HttpStatus.OK);
  }

  @PostMapping("/ticketValidation")
  public ResponseEntity<TicketToken> validateTicketToken(@RequestBody TicketToken ticketToken) throws CustomException {
    return new ResponseEntity<TicketToken>(service.validateTicketToken(ticketToken), HttpStatus.OK);
  }

  @PostMapping("/cancel/{ticketToken}")
  public ResponseEntity<Ticket> cancelTicket(@RequestBody String ticketToken) throws CustomException {
    return new ResponseEntity<Ticket>(ticketService.updateCancellationStatus(ticketToken), HttpStatus.OK);
  }
}