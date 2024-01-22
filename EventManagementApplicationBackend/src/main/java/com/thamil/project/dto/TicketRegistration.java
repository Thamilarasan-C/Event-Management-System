package com.thamil.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRegistration {
  private Long userId;
  private Long eventId;
  private List<AttendeeDto> attendees;
}
