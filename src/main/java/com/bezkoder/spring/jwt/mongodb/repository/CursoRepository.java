package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Curso;

public interface CursoRepository extends MongoRepository<Curso, String> {
  List<Curso> findByCodigoContaining(String codigo);
  List<Curso> findByRamoidContaining(String ramoid);
  String deleteByRamoid(String ramoid);
}