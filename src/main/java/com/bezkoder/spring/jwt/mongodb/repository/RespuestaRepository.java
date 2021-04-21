package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Respuesta;

public interface RespuestaRepository extends MongoRepository<Respuesta, String> {
    String deleteByUsuarioid(String usuarioid);
    String deleteByPreguntaid(String preguntaid);
    String deleteByQuizid(String quizid);
    List<Respuesta> findByPreguntaidContaining(String preguntaid);
    List<Respuesta> findByUsuarioidContaining(String usuarioid);
    List<Respuesta> findByQuizidContaining(String quizid);
}