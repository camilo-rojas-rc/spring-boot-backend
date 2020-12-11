package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.QuizPre;

public interface QuizPreRepository extends MongoRepository<QuizPre, String> {
}