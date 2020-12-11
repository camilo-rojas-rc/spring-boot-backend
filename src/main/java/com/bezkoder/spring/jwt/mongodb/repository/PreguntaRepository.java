package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Pregunta;

public interface PreguntaRepository extends MongoRepository<Pregunta, String> {
  List<Pregunta> findByTituloContaining(String titulo);
}
