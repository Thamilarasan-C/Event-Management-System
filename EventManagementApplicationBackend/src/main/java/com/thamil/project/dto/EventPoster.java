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
public class EventPoster {
  private String eventName;
  private LocalDate date;
  private String description;
  private String venue;
  private int ticketsAvailable;
  private int ticketPrice;
}
