package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.TagPre;

public interface TagPreRepository extends MongoRepository<TagPre, String> {
    String deleteByTagid(String tagid);
    String deleteByPreguntaid(String preguntaid);
    List<TagPre> findByPreguntaidContaining(String preguntaid);
    List<TagPre> findByTagidContaining(String tagid);
    Optional<TagPre> findByPreguntaid(String preguntaid);
}