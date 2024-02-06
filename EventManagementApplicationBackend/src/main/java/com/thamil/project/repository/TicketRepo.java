package com.thamil.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thamil.project.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

  @Query("UPDATE Ticket t set t.isAttended = :isPresent WHERE t.ticketId = :ticketId")
  Ticket updateStatus(Long ticketId, boolean isPresent);

  @Query("UPDATE Ticket t set t.isAttended = :isPresent WHERE t.ticketToken = :ticketToken")
  Ticket updateStatus(String ticketToken, boolean isPresent);

  boolean existsByTicketToken(String ticketTokenString);

  Optional<Ticket> findByTicketToken(String userName);

  @Query("UPDATE Ticket t set t.isCancelled = :isPresent WHERE t.ticketToken = :ticketToken")
  Ticket updateCancellationStatus(String ticketToken, boolean isPresent);

}
