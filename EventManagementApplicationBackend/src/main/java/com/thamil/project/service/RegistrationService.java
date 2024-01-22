package com.thamil.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Registration;
import com.thamil.project.repository.RegistrationRepo;

@Service
public class RegistrationService {

  @Autowired
  private RegistrationRepo repo;

  @Autowired
  private TicketDetailsService ticketDetailsService;

  public Registration saveRegistration(Registration registration) throws CustomException {
    registration.setDate(LocalDate.now());
    registration.setTime(LocalTime.now());
    System.out.println("vhujghhikh  save regi 5" + registration.getEventId());
    ticketDetailsService.updateTicketCounts(registration.getEventId(), registration.getTicketCount());
    return repo.save(registration);
  }

  public List<Registration> getRegistrationsByUserId(Long userId) {
    return repo.findRegistrationsByUserId(userId);
  }
}