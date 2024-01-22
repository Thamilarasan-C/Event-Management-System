package com.thamil.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Event;
import com.thamil.project.model.Ticket;
import com.thamil.project.service.TicketService;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("ticket")
public class TicketController {
  
  @Autowired
  private TicketService service;
  
  @PostMapping("createTicket")
  public ResponseEntity<Ticket> insertTicket(@RequestBody Ticket ticket){
    Ticket t = service.saveTicket(ticket);
    return new ResponseEntity<Ticket>(t,HttpStatus.OK);
  }

  @PatchMapping("/updateStatus/{ticketId}/status/{isPresent}")
  public ResponseEntity<Ticket> updateStatus(@PathVariable Long ticketId, @PathVariable boolean isPresent)
      throws CustomException {
    Ticket updatedEvent = service.updateStatus(ticketId, isPresent);
    return new ResponseEntity<Ticket>(updatedEvent, HttpStatus.OK);
  }
}