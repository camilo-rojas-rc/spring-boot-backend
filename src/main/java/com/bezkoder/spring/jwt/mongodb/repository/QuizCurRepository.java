package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.QuizCur;

public interface QuizCurRepository extends MongoRepository<QuizCur, String> {
    String deleteByCursoid(String cursoid);
    String deleteByQuizid(String quizid);
}