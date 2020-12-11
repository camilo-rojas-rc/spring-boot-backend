package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Recurso;

public interface RecursoRepository extends MongoRepository<Recurso, String> {

}