package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.model.CarreRamo;

public interface CarreRamoRepository extends MongoRepository<CarreRamo, String> {
    String deleteByCarreraid(String carreraid);
    String deleteByRamoid(String ramoid);
}