package com.thamil.project.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attendees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long attendeeId;
  private String emailId;
  private String name;
  private String gender;
  private LocalDate dob;
}