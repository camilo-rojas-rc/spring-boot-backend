package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Ramo;

public interface RamoRepository extends MongoRepository<Ramo, String> {
  List<Ramo> findByNombreContaining(String nombre);
}