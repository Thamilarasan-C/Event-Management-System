package com.thamil.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thamil.project.model.Registration;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Long> {

  @Query("SELECT r FROM Registration r WHERE r.userId = :userId")
  List<Registration> findRegistrationsByUserId(Long userId);
}
