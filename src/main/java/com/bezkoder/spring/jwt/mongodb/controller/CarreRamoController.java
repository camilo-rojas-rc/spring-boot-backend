package com.bezkoder.spring.jwt.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.model.CarreRamo;
import com.bezkoder.spring.jwt.mongodb.repository.CarreRamoRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CarreRamoController {

  @Autowired
  CarreRamoRepository carreramoRepository;

  @GetMapping("/carreramos/all")
  public ResponseEntity<List<CarreRamo>> getAllCarreRamos() {
    try {
      List<CarreRamo> carreramo = new ArrayList<CarreRamo>();

      carreramoRepository.findAll().forEach(carreramo::add);
  
      if (carreramo.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(carreramo, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/carreramos/add")
  public ResponseEntity<CarreRamo> createCarreRamo(@RequestBody CarreRamo CarreRamo) {
    try {
      CarreRamo carreramo = new CarreRamo(CarreRamo.getCarreraid(), CarreRamo.getRamoid());
		  carreramoRepository.save(carreramo);
      return new ResponseEntity<>(carreramo, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/carreramos/{id}")
  public ResponseEntity<CarreRamo> updateCarreRamo(@PathVariable("id") String id, @RequestBody CarreRamo carreramo) {
    Optional<CarreRamo> carreramoData = carreramoRepository.findById(id);
  
    if (carreramoData.isPresent()) {
      CarreRamo _carreramo  = carreramoData.get();
      _carreramo .setCarreraid(carreramo.getCarreraid());
      _carreramo .setRamoid(carreramo.getRamoid());
      return new ResponseEntity<>(carreramoRepository.save(_carreramo ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/carreramos/{id}")
  public ResponseEntity<HttpStatus> deleteCarreRamo(@PathVariable("id") String id) {
    try {
      carreramoRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}