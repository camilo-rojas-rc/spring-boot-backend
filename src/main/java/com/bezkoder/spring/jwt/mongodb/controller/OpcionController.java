package com.bezkoder.spring.jwt.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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

import com.bezkoder.spring.jwt.mongodb.model.Opcion;
import com.bezkoder.spring.jwt.mongodb.repository.OpcionRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OpcionController {

  @Autowired
  OpcionRepository opcionRepository;

  @GetMapping("/opcions/all")
  public ResponseEntity<List<Opcion>> getAllOpcions(@RequestParam(required = false) String opcion) {
    try {
      List<Opcion> opcions = new ArrayList<Opcion>();
  
      if (opcion == null)
          opcionRepository.findAll().forEach(opcions::add);
      else
          opcionRepository.findByOpcionContaining(opcion).forEach(opcions::add);
  
      if (opcions.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(opcions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/opcions/{id}")
  public ResponseEntity<Opcion> getOpcionById(@PathVariable("id") String id) {
    Optional<Opcion> opcionData = opcionRepository.findById(id);
  
    if (opcionData.isPresent()) {
      return new ResponseEntity<>(opcionData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/opcions/add")
  public ResponseEntity<Opcion> createOpcion(@RequestBody Opcion opcion) {
    try {
      Opcion _opcion = opcionRepository.save(new Opcion(opcion.getOpcion(), opcion.getCoincide(), opcion.getPorcentaje(), opcion.getPregunta()));
      return new ResponseEntity<>(_opcion, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/opcions/{id}")
  public ResponseEntity<Opcion> updateOpcion(@Valid @PathVariable("id") String id, @RequestBody Opcion opcion) {
    Optional<Opcion> opcionData = opcionRepository.findById(id);
  
    if (opcionData.isPresent()) {
      Opcion _opcion  = opcionData.get();
      _opcion.setOpcion(opcion.getOpcion());
      _opcion.setCoincide(opcion.getCoincide());
      _opcion.setPorcentaje(opcion.getPorcentaje());
      _opcion.setOpcion(opcion.getPregunta());
      return new ResponseEntity<>(opcionRepository.save(_opcion ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/opcions/{id}")
  public ResponseEntity<HttpStatus> deleteOpcion(@PathVariable("id") String id) {
    try {
      opcionRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}