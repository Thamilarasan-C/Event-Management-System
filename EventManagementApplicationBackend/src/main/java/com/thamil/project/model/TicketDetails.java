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
@Table(name = "ticketdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticketDetailsId;
  private int price;
  private Long eventId;
  @ManyToOne
  @JoinColumn(name = "eventId", insertable = false, updatable = false)
  private Event event;
  private int totalTicketCount;
  private int ticketsAvailable;
}
