package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Retroalimentacion;

public interface RetroalimentacionRepository extends MongoRepository<Retroalimentacion, String> {
    List<Retroalimentacion> findByTipoContaining(String tipo);
    String deleteByPreguntaid(String preguntaid);
}