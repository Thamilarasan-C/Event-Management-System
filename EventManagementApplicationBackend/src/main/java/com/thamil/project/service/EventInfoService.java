package com.thamil.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamil.project.dto.EventInfo;
import com.thamil.project.dto.EventPoster;
import com.thamil.project.model.Event;
import com.thamil.project.model.TicketDetails;
import com.thamil.project.repository.EventRepo;
import com.thamil.project.repository.TicketDetailsRepo;

@Service
public class EventInfoService {

  @Autowired
  private EventRepo eventRepo;

  @Autowired
  private TicketDetailsRepo ticketDetailsRepo;

  public String saveEvent(EventInfo eventInfo) {
    Event event = eventFromEventInfo(eventInfo);
    TicketDetails ticketDetails = ticketDetailsFromEventInfo(eventInfo);
    ticketDetails.setTicketsAvailable(eventInfo.getTotalTicketCount());
    Event createdEvent = eventRepo.save(event);
    ticketDetails.setEventId(createdEvent.getEventId());
    ticketDetailsRepo.save(ticketDetails);
    return "Event Successfuly added";
  }

  public Event eventFromEventInfo(EventInfo eventInfo) {
    return Event.builder()
        .eventName(eventInfo.getEventName())
        .date(eventInfo.getDate())
        .description(eventInfo.getDescription())
        .venue(eventInfo.getVenue())
        .organizerId(eventInfo.getOrganizerId())
        .status("upComing")
        .build();
  }

  public TicketDetails ticketDetailsFromEventInfo(EventInfo eventInfo) {
    return TicketDetails.builder()
        .price(eventInfo.getTicketPrice())
        .totalTicketCount(eventInfo.getTotalTicketCount())
        .build();
  }

  public List<EventInfo> getAllEventsInfo() {
    List<EventInfo> eventsInfo = new ArrayList<EventInfo>();
    List<Event> events = eventRepo.findAll();
    for (Event event : events) {

      Optional<TicketDetails> ticketsDetail = ticketDetailsRepo.findByEventId(event.getEventId());

      TicketDetails ticketDetails = ticketsDetail.get();

      EventInfo eventInfo = EventInfo.builder().eventName(event.getEventName()).date(event.getDate())
          .description(event.getDescription()).totalTicketCount(ticketDetails.getTotalTicketCount())
          .ticketPrice(ticketDetails.getPrice()).venue(event.getVenue()).build();

      eventsInfo.add(eventInfo);
    }
    return eventsInfo;
  }

  public List<EventPoster> getEventsInfo(String status) {
    List<EventPoster> eventPosters = new ArrayList<EventPoster>();
    List<Event> events = eventRepo.getEvents(status);

    for (Event event : events) {

      System.out.println("Event id: " + event.getEventId());

      Optional<TicketDetails> ticketsDetail = ticketDetailsRepo.findByEventId(event.getEventId());

      TicketDetails ticketDetails = ticketsDetail.get();

      EventPoster eventPoster = EventPoster.builder()
          .eventName(event.getEventName())
          .date(event.getDate())
          .description(event.getDescription())
          .ticketsAvailable(ticketDetails.getTicketsAvailable())
          .ticketPrice(ticketDetails.getPrice())
          .venue(event.getVenue()).build();
      eventPosters.add(eventPoster);
    }
    return eventPosters;
  }
}