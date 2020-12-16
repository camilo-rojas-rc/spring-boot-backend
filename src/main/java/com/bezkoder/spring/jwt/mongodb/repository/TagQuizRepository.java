package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.TagQuiz;

public interface TagQuizRepository extends MongoRepository<TagQuiz, String> {
    String deleteByTagid(String tagid);
    String deleteByQuizid(String quizid);
}