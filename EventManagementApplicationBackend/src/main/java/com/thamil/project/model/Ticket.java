package com.thamil.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticketId;
  private String ticketToken;
  private Long registrationId;
  @ManyToOne
  @JoinColumn(name = "registrationId", insertable = false, updatable = false)
  private Registration registration;
  private Long attendeeId;
  @ManyToOne
  @JoinColumn(name = "attendeeId", insertable = false, updatable = false)
  private Attendee attendee;
  private Long ticketDetailsId;
  @ManyToOne
  @JoinColumn(name = "ticketDetailsId", insertable = false, updatable = false)
  private TicketDetails ticketdetails;
  private boolean isAttended;
}
