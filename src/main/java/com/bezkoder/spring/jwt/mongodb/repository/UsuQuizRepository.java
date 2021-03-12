package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.UsuQuiz;

public interface UsuQuizRepository extends MongoRepository<UsuQuiz, String> {
    String deleteByUsuarioid(String usuarioid);
    String deleteByQuizid(String quizid);
    List<UsuQuiz> findByQuizidContaining(String quizid);
}