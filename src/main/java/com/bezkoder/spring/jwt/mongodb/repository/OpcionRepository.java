package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Opcion;

public interface OpcionRepository extends MongoRepository<Opcion, String> {
  List<Opcion> findByOpcionContaining(String opcion);
}