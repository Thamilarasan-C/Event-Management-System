package com.thamil.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventInfo {
  private Long eventId;
  private String eventName;
  private LocalDate date;
  private String description;
  private String venue;
  private Long organizerId;
  private int totalTicketCount;
  private int ticketPrice;
}
