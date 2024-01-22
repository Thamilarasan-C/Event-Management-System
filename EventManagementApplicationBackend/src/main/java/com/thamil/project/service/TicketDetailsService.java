package com.thamil.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.TicketDetails;
import com.thamil.project.repository.EventRepo;
import com.thamil.project.repository.TicketDetailsRepo;

import jakarta.transaction.Transactional;
 
@Service
public class TicketDetailsService {

  @Autowired
  private TicketDetailsRepo repo;

  @Autowired
  private EventRepo eventRepo;

  public TicketDetails saveTicketDetails(TicketDetails ticketDetails) {
    return repo.save(ticketDetails);
  }

  @Transactional
  public void updateTicketCounts(Long eventId, int bookedTicketCount) throws CustomException{
    if(eventId != null && eventRepo.existsById(eventId))
    repo.updateTicketCount(eventId,bookedTicketCount);
    else
    throw new CustomException("No event details found with this eventId");
  }
  public TicketDetails getTicketDetails(Long ticketDetailsId) throws CustomException {
    Optional<TicketDetails> ticketDetails = repo.findById(ticketDetailsId);
    if(ticketDetails.isPresent())
    return ticketDetails.get();
    throw new CustomException("No ticket details found with this ticketId");
  }
}
