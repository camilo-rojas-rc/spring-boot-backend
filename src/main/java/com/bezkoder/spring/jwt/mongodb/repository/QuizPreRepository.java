package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.QuizPre;

public interface QuizPreRepository extends MongoRepository<QuizPre, String> {
    String deleteByQuizid(String quizid);
    String deleteByPreguntaid(String preguntaid);
    List<QuizPre> findByQuizidContaining(String quizid);
    List<QuizPre> findByPreguntaidContaining(String preguntaid);
}