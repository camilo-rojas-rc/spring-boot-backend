package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String> {
    String deleteByUsers(String users);
}