package com.thamil.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long registrationId;
  private LocalDate date;
  private LocalTime time;
  private int ticketCount;
  private Long userId;
  @ManyToOne
  @JoinColumn(name = "userId", insertable = false, updatable = false)
  private User user;
  private Long eventId;
  @ManyToOne
  @JoinColumn(name = "eventId", insertable = false, updatable = false)
  private Event event;
}