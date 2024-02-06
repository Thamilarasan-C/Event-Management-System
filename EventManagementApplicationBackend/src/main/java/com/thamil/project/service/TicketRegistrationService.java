package com.thamil.project.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.dto.AttendeeDto;
import com.thamil.project.dto.TicketRegistration;
import com.thamil.project.dto.TicketToken;
import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Attendee;
import com.thamil.project.model.Registration;
import com.thamil.project.model.Ticket;
import com.thamil.project.model.TicketDetails;
import com.thamil.project.repository.AttendeeRepo;
import com.thamil.project.repository.TicketDetailsRepo;
import com.thamil.project.repository.TicketRepo;

@Service
public class TicketRegistrationService {

  @Autowired
  private AttendeeRepo attendeeRepo;

  @Autowired
  private RegistrationService registrationService;

  @Autowired
  private TicketDetailsRepo ticketDetailsRepo;

  @Autowired
  private TicketRepo ticketRepo;

  private TicketDetailsService ticketDetailsService;

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int STRING_LENGTH = 10;

  private static final SecureRandom random = new SecureRandom();

  public List<TicketToken> saveTicketRegistration(TicketRegistration ticketRegistration) throws CustomException {

    List<TicketToken> ticketTokens = new ArrayList<TicketToken>();

    int ticketsAvailable = ticketDetailsService.findTicketsAvailable(ticketRegistration.getEventId());

    if (ticketsAvailable > ticketRegistration.getAttendees().size())
      throw new CustomException("Only " + ticketsAvailable + "tickets available");

    Registration registration = Registration.builder()
        .userId(ticketRegistration.getUserId())
        .eventId(ticketRegistration.getEventId())
        .ticketCount(ticketRegistration.getAttendees().size())
        .build();

    Long regId = registrationService.saveRegistration(registration).getRegistrationId();

    for (AttendeeDto currentAttendeeDto : ticketRegistration.getAttendees()) {
      Long attendeeId;
      if (!attendeeRepo.existsByEmailId(currentAttendeeDto.getEmailId()))
        attendeeId = attendeeRepo.save(attendeeFromDto(currentAttendeeDto)).getAttendeeId();
      else {
        attendeeId = attendeeRepo.findByEmailId(currentAttendeeDto.getEmailId()).get().getAttendeeId();
      }
      Optional<TicketDetails> ticketDetails = ticketDetailsRepo.findByEventId(ticketRegistration.getEventId());
      Long ticketDetailsId = ticketDetails.get().getTicketDetailsId();
      String ticketTokenString = generateUniqueTicketToken();
      Ticket ticket = Ticket.builder()
          .registrationId(regId)
          .attendeeId(attendeeId)
          .ticketToken(ticketTokenString)
          .ticketDetailsId(ticketDetailsId)
          .isAttended(false)
          .isCancelled(false)
          .build();
      ticketRepo.save(ticket);
      ticketTokens
          .add(new TicketToken(currentAttendeeDto.getName(), currentAttendeeDto.getEmailId(), ticketTokenString));
    }
    return ticketTokens;
  }

  public Attendee attendeeFromDto(AttendeeDto attendeeDto) {
    return Attendee.builder()
        .emailId(attendeeDto.getEmailId())
        .name(attendeeDto.getName())
        .gender(attendeeDto.getGender())
        .dob(attendeeDto.getDob())
        .build();
  }

  private String generateUniqueTicketToken() {
    String randomString;
    do {
      StringBuilder stringBuilder = new StringBuilder(STRING_LENGTH);
      for (int i = 0; i < STRING_LENGTH; i++) {
        int randomIndex = random.nextInt(CHARACTERS.length());
        char randomChar = CHARACTERS.charAt(randomIndex);
        stringBuilder.append(randomChar);
      }
      randomString = stringBuilder.toString();
    } while (ticketRepo.existsByTicketToken(randomString));
    return randomString;
  }

  public TicketToken validateTicketToken(TicketToken ticketToken) throws CustomException {
    String tokenString = ticketToken.getTicketToken();
    Optional<Ticket> ticket = ticketRepo.findByTicketToken(tokenString);
    if (!ticket.isPresent())
      throw new CustomException("Invalid ticket");
    return ticketToken;
  }
}
