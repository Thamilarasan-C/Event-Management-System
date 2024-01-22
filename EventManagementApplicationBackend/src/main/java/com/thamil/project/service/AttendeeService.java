package com.thamil.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.model.Attendee;
import com.thamil.project.repository.AttendeeRepo;

@Service
public class AttendeeService {
  
  @Autowired
  private AttendeeRepo repo;

  public Attendee saveAttendee(Attendee attendee){
    return repo.save(attendee);
  }

  public List<Attendee> findAttendeesByEventId(Long eventId){
    return repo.findAttendeesByEventId(eventId);
  }
}