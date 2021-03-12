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

import com.bezkoder.spring.jwt.mongodb.model.Carrera;
import com.bezkoder.spring.jwt.mongodb.model.CarreUsu;
import com.bezkoder.spring.jwt.mongodb.repository.CarreraRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CarreUsuRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CarreRamoRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CarreraController {

  @Autowired
  CarreraRepository carreraRepository;

  @Autowired
  CarreUsuRepository carreusuRepository;

  @Autowired
  CarreRamoRepository carreramoRepository;

  @GetMapping("/carreras/all")
  public ResponseEntity<List<Carrera>> getAllCarreras(@RequestParam(required = false) String malla) {
    try {
      List<Carrera> carreras = new ArrayList<Carrera>();
  
      if (malla == null)
        carreraRepository.findAll().forEach(carreras::add);
      else
        carreraRepository.findByMallaContaining(malla).forEach(carreras::add);
  
      if (carreras.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(carreras, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/carreras/{id}")
  public ResponseEntity<Carrera> getCarreraById(@PathVariable("id") String id) {
    Optional<Carrera> carreraData = carreraRepository.findById(id);
  
    if (carreraData.isPresent()) {
      return new ResponseEntity<>(carreraData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/carreras/add")
  public ResponseEntity<Carrera> createCarrera(@RequestBody Carrera Carrera) {
    try {
      Carrera carrera = new Carrera(Carrera.getMalla());
	    carreraRepository.save(carrera);
      return new ResponseEntity<>(carrera, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/carreras/{id}")
  public ResponseEntity<Carrera> updateCarrera(@PathVariable("id") String id, @RequestBody Carrera carrera) {
    Optional<Carrera> carreraData = carreraRepository.findById(id);
  
    if (carreraData.isPresent()) {
      Carrera _carrera  = carreraData.get();
      _carrera .setMalla(carrera.getMalla());
      return new ResponseEntity<>(carreraRepository.save(_carrera ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/carreras/{id}")
  public ResponseEntity<HttpStatus> deleteCarrera(@PathVariable("id") String id) {
    try {
      carreusuRepository.deleteByCarreraid(id);
      carreramoRepository.deleteByCarreraid(id);
      carreraRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}