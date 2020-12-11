package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {
  List<Tag> findByNombreContaining(String nombre);
}