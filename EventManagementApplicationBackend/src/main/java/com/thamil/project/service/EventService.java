package com.thamil.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Event;
import com.thamil.project.repository.EventRepo;

@Service
public class EventService {

  @Autowired
  private EventRepo repo;

  public Event saveEvent(Event event) {
    return repo.save(event);
  }

  public List<Event> getEvents(String status) {
    return repo.getEvents(status);
  }

  public Event updateEvent(Event event) throws CustomException {
    if (repo.existsById(event.getEventId()))
      return repo.save(event);
    throw new CustomException("No events found with this eventId");
  }

  public Event updateStatus(Long eventId, String status) throws CustomException {
    Optional<Event> event = repo.findById(eventId);
    if (!event.isPresent())
      throw new CustomException("Event not found in this eventId");
    Event updatedevent = event.get();
    updatedevent.setStatus(status);
    return repo.save(updatedevent);
  }

  public Event updateDate(Long eventId, LocalDate date) throws CustomException {
    Optional<Event> event = repo.findById(eventId);
    if (!event.isPresent())
      throw new CustomException("Event not found in this eventId");
    Event updatedevent = event.get();
    updatedevent.setDate(date);
    return repo.save(updatedevent);
  }

  public Event updateVenue(Long eventId, String venue) throws CustomException {
    Optional<Event> event = repo.findById(eventId);
    if (!event.isPresent())
      throw new CustomException("Event not found in this eventId");
    Event updatedevent = event.get();
    updatedevent.setVenue(venue);
    return repo.save(updatedevent);
  }

  public Event updateDescription(Long eventId, String description) throws CustomException {
    Optional<Event> event = repo.findById(eventId);
    if (!event.isPresent())
      throw new CustomException("Event not found in this eventId");
    Event updatedevent = event.get();
    updatedevent.setVenue(description);
    return repo.save(updatedevent);
  }

  public List<Event> getEventsByAttendeeId(Long attendeeId) {
    return repo.findEventsByAttendeeId(attendeeId);
  }

  public List<Event> getEventsByOrganizerId(Long organizerId) {
    return repo.findEventsByOrganizerId(organizerId);
  }
}
