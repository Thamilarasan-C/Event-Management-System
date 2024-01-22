package com.thamil.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamil.project.dto.EventInfo;
import com.thamil.project.dto.EventPoster;
import com.thamil.project.service.EventInfoService;

@RestController
@RequestMapping("eventInfo")
public class EventInfoController {

  @Autowired
  private EventInfoService service;

  @PostMapping("/saveEventInfo")
  public ResponseEntity<String> insertEvent(@RequestBody EventInfo eventInfo) {
    return new ResponseEntity<String>(service.saveEvent(eventInfo), HttpStatus.OK);
  }

  @GetMapping("/getAllEventInfo")
  public ResponseEntity<List<EventInfo>> getAllEvents() {
    return new ResponseEntity<List<EventInfo>>(service.getAllEventsInfo(), HttpStatus.OK);
  }

  @GetMapping("/getEventPosters/{status}")
  public ResponseEntity<List<EventPoster>> getEventsInfo(@PathVariable String status) {
    return new ResponseEntity<List<EventPoster>>(service.getEventsInfo(status), HttpStatus.OK);
  }
}
