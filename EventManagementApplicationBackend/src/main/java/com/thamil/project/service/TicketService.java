package com.thamil.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.model.Ticket;
import com.thamil.project.repository.TicketRepo;

@Service
public class TicketService {
  
  @Autowired
  private TicketRepo repo;

  public Ticket saveTicket(Ticket ticket){
    return repo.save(ticket);
  }

  public Ticket updateStatus(Long ticketId, boolean isPresent) {
    return repo.updateStatus(ticketId,isPresent);
  }

}
