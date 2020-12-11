package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
  Optional<User> findById(String id);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  List<User> findByUsernameContaining(String Username);
}
