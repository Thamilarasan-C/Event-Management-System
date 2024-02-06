package com.thamil.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Ticket;
import com.thamil.project.repository.TicketRepo;

@Service
public class TicketService {

  @Autowired
  private TicketRepo repo;

  @Autowired
  private TicketDetailsService ticketDetailsService;

  public Ticket saveTicket(Ticket ticket) {
    return repo.save(ticket);
  }

  public Ticket updateStatus(Long ticketId, boolean isPresent) {
    return repo.updateStatus(ticketId, isPresent);
  }

  public Ticket updateCancellationStatus(String ticketTokenString) throws CustomException {
    Optional<Ticket> ticket = repo.findByTicketToken(ticketTokenString);
    if (!ticket.isPresent())
      throw new CustomException("Invalid ticketToken");
    ticketDetailsService.updateTicketCountByOne(ticketTokenString);
    return repo.updateCancellationStatus(ticketTokenString, true);
  }
}
