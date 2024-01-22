package com.thamil.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thamil.project.config.JwtService;
import com.thamil.project.dto.LoginRequest;
import com.thamil.project.dto.LoginResponse;
import com.thamil.project.dto.SignUpRequest;
import com.thamil.project.exception.CustomException;
import com.thamil.project.model.User;
import com.thamil.project.repository.UserRepo;

@Service
public class UserService {
  @Autowired
  private UserRepo repo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;
  public LoginResponse saveUser(SignUpRequest signUpRequest) throws CustomException {

    if (signUpRequest.getEmailId() == null)
      throw new CustomException("Email Id must not be null");

    if (signUpRequest.getEmailId() != null && repo.existsByEmailId(signUpRequest.getEmailId()))
      throw new CustomException("Email Id already registered");

    User user = User.builder()
        .emailId(signUpRequest.getEmailId())
        .name(signUpRequest.getName())
        .password(passwordEncoder.encode(signUpRequest.getPassword()))
        .role(signUpRequest.getRole())
        .build();

    repo.save(user);
    return new LoginResponse(jwtService.generateToken(user));
  }

  public LoginResponse validateUser(LoginRequest loginRequest) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailId(),loginRequest.getPassword()));
    User user = repo.findByEmailId(loginRequest.getEmailId()).orElseThrow();
    return new LoginResponse(jwtService.generateToken(user));
  }

  // public User validateUser(LoginRequest loginRequest) throws CustomException {

  //   if (!repo.existsByName(loginRequest.getUserName()))
  //     throw new CustomException("User name not found");

  //   Optional<User> user = repo.findByName(loginRequest.getUserName());

  //   if (loginRequest.getPassword().equals(user.get().getPassword()))
  //     return user.get();

  //   throw new CustomException("Incorrect Password");
  // }

  // public LoginResponse generateToken(User user) {
  //   Claims claims = Jwts.claims().setSubject(user.getName());
  //   claims.put("role", user.getRole());
  //   claims.put("userId", user.getId());
  //   String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,
  //       "ur97q2e7r2934892rnu213rn09217349782190348y12").compact();
  //   return new LoginResponse(token);
  // }
}