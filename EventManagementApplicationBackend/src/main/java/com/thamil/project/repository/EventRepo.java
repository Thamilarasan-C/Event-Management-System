package com.thamil.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thamil.project.model.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
  @Query("SELECT e FROM Event e WHERE e.status = :status")
  List<Event> getEvents(@Param("status") String status);

  @Query("SELECT e FROM Event e JOIN Registration r ON e.eventId = r.eventId JOIN Ticket t ON r.registrationId = t.registrationId WHERE t.attendeeId = :attendeeId")
  List<Event> findEventsByAttendeeId(@Param("attendeeId") Long attendeeId);

  @Query("SELECT e FROM Event e WHERE e.organizerId = :organizerId")
  List<Event> findEventsByOrganizerId(@Param("organizerId") Long organizerId);

}
