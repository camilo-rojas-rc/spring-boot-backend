package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.TagPre;

public interface TagPreRepository extends MongoRepository<TagPre, String> {
    Optional<TagPre> findByTagid(String tagid);
    Optional<TagPre> findByPreguntaid(String preguntaid);
}