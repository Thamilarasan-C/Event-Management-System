package com.thamil.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketToken {
  String attendeeName;
  String attendeeEmailId;
  String ticketToken;
}
