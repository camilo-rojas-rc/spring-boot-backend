package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.PreRecur;

public interface PreRecurRepository extends MongoRepository<PreRecur, String> {
    String deleteByRecursoid(String recursoid);
    String deleteByPreguntaid(String preguntaid);
}