package com.thamil.project.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.exception.CustomException;
import com.thamil.project.model.Event;
import com.thamil.project.service.EventService;

@RestController
@RequestMapping(path = "event")
public class EventController {

  @Autowired
  private EventService service;

  @PostMapping("/saveEvent")
  public ResponseEntity<Event> insertEvent(@RequestBody Event event) {
    Event e = service.saveEvent(event);
    return new ResponseEntity<Event>(e, HttpStatus.OK);
  }

  @PutMapping("/updateEvent")
  public ResponseEntity<Event> updateEvent(@RequestBody Event event) throws CustomException {
    Event e = service.updateEvent(event);
    return new ResponseEntity<Event>(e, HttpStatus.OK);
  }

  @GetMapping("/getEvents/{status}")
  public ResponseEntity<List<Event>> selectEventsByStatus(@PathVariable String status) {
    List<Event> events = service.getEvents(status);
    if (events == null) {
      return new ResponseEntity<>(new ArrayList<Event>(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @GetMapping("/getEvents/organizer/{organizerId}")
  public ResponseEntity<List<Event>> selectEventsByOrganizer(@PathVariable Long organizerId) {
    List<Event> events = service.getEventsByOrganizerId(organizerId);
    if (events == null) {
      return new ResponseEntity<>(new ArrayList<Event>(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @GetMapping("/getEvents/attendee/{attendeeId}")
  public ResponseEntity<List<Event>> selectEvents(@PathVariable Long attendeeId) {
    List<Event> events = service.getEventsByAttendeeId(attendeeId);
    if (events == null) {
      return new ResponseEntity<>(new ArrayList<Event>(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @PatchMapping("/{eventId}/updateStatus/{status}")
  public ResponseEntity<Event> updateStatus(@PathVariable Long eventId, @PathVariable String status)
      throws CustomException {
    Event updatedEvent = service.updateStatus(eventId, status);
    return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
  }

  @PatchMapping("/{eventId}/updateDate/{date}")
  public ResponseEntity<Event> updateStatus(@PathVariable Long eventId, @PathVariable LocalDate date)
      throws CustomException {
    Event updatedEvent = service.updateDate(eventId, date);
    return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
  }

  @PatchMapping("/{eventId}/updateVenue/{venue}")
  public ResponseEntity<Event> updateVenue(@PathVariable Long eventId, @PathVariable String venue)
      throws CustomException {
    Event updatedEvent = service.updateVenue(eventId, venue);
    return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
  }

  @PatchMapping("/{eventId}/updateDescription/{description}")
  public ResponseEntity<Event> updateDescription(@PathVariable Long eventId, @PathVariable String description)
      throws CustomException {
    Event updatedEvent = service.updateDescription(eventId, description);
    return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
  }
}