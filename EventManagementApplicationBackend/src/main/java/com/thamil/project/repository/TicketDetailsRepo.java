package com.thamil.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thamil.project.model.TicketDetails;

@Repository
public interface TicketDetailsRepo extends JpaRepository<TicketDetails, Long> {
  // @Query("UPDATE TicketDetails t SET t.ticketsAvailable = t.ticketsAvailable -
  // :bookedTicketCount WHERE t.eventId = :eventId")
  // void updateTicketCount(@Param("eventId") Long eventId ,
  // @Param("bookedTicketCount") int availableTicketCount);

  @Modifying
  @Query("UPDATE TicketDetails t SET t.ticketsAvailable = t.ticketsAvailable - :bookedTicketCount WHERE t.eventId = :eventId")
  void updateTicketCount(@Param("eventId") Long eventId, @Param("bookedTicketCount") int bookedTicketCount);

  Optional<TicketDetails> findByEventId(Long eventId);

  @Modifying
  @Query("UPDATE TicketDetails td JOIN Ticket t ON td.ticketDetailsId = t.ticketDetailsId JOIN Registration r ON t.registrationId = r.registrationId JOIN Event e ON r.eventId = e.eventId SET td.ticketsAvailable = td.ticketsAvailable + 1 WHERE t.ticketToken = :ticketToken;")
  void increaseTicketCount(String ticketToken);

}