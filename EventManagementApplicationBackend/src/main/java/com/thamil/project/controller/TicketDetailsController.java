package com.thamil.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.TicketDetails;
import com.thamil.project.service.TicketDetailsService;



@RestController
@RequestMapping(path = "ticketDetails")
public class TicketDetailsController {
  
  @Autowired
  private TicketDetailsService service;

  @ExceptionHandler(InternalError.class)
  public ResponseEntity<String> handleException(Exception e) {
    return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @PostMapping("createTicketDetails")
  public ResponseEntity<TicketDetails> insertTicketDetails(@RequestBody TicketDetails ticketDetails) {
    TicketDetails t = service.saveTicketDetails(ticketDetails);
     return new ResponseEntity<>(t, HttpStatus.OK);
  }
  
  @PostMapping("TicketDetails/{ticketDetailsId}")
  public ResponseEntity<TicketDetails> getTicketDetails(@RequestBody Long ticketDetailsId) throws CustomException {
    TicketDetails t = service.getTicketDetails(ticketDetailsId);
     return new ResponseEntity<>(t, HttpStatus.OK);
  }
}
