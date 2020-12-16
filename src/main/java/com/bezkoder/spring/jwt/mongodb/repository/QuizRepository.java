package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByTituloContaining(String titulo);
    String deleteByCursoid(String cursoid);
}