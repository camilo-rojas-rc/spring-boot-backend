package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Carrera;

public interface CarreraRepository extends MongoRepository<Carrera, String> {
  List<Carrera> findByMallaContaining(String malla);
}