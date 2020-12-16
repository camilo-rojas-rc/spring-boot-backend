package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.CurUsu;

public interface CurUsuRepository extends MongoRepository<CurUsu, String> {
    String deleteByCursoid(String cursoid);
    String deleteByUsuarioid(String usuarioid);
}