package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Recurso;

public interface RecursoRepository extends MongoRepository<Recurso, String> {
    List<Recurso> findByTypeContaining(String type);
}