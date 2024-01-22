package com.thamil.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thamil.project.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

  boolean existsByEmailId(String emailId);

  boolean existsByName(String name);

  Optional<User> findByName(String userName);

  Optional<User> findByEmailId(String emailId);

}
