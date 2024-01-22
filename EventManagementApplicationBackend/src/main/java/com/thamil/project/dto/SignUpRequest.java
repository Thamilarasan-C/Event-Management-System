package com.thamil.project.dto;

import com.thamil.project.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
  private String emailId;
  private String name;
  private String password;
  private String role;
}
