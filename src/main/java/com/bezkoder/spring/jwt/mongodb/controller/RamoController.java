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

import com.bezkoder.spring.jwt.mongodb.model.Ramo;
import com.bezkoder.spring.jwt.mongodb.model.Curso;
import com.bezkoder.spring.jwt.mongodb.repository.RamoRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CurUsuRepository;
import com.bezkoder.spring.jwt.mongodb.repository.QuizCurRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CarreRamoRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CursoRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RamoController {

  @Autowired
  RamoRepository ramoRepository;

  @Autowired
  CarreRamoRepository carreramoRepository;

  @Autowired
  CursoRepository cursoRepository;

  @Autowired
  CurUsuRepository curusuRepository;

  @Autowired
  QuizCurRepository quizcurRepository;

  @GetMapping("/ramos/all")
  public ResponseEntity<List<Ramo>> getAllRamos(@RequestParam(required = false) String nombre) {
    try {
      List<Ramo> ramos = new ArrayList<Ramo>();
  
      if (nombre == null)
          ramoRepository.findAll().forEach(ramos::add);
      else
          ramoRepository.findByNombreContaining(nombre).forEach(ramos::add);
  
      if (ramos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(ramos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/ramos/{id}")
  public ResponseEntity<Ramo> getRamoById(@PathVariable("id") String id) {
    Optional<Ramo> ramoData = ramoRepository.findById(id);
  
    if (ramoData.isPresent()) {
      return new ResponseEntity<>(ramoData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/ramos/add")
  public ResponseEntity<Ramo> createRamo(@RequestBody Ramo ramo) {
    try {
      Ramo _ramo = ramoRepository.save(new Ramo(ramo.getCodigo(), ramo.getNombre(), ramo.getSemestre(), ramo.getDescripcion()));
      return new ResponseEntity<>(_ramo, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/ramos/{id}")
  public ResponseEntity<Ramo> updateRamo(@Valid @PathVariable("id") String id, @RequestBody Ramo ramo) {
    Optional<Ramo> ramoData = ramoRepository.findById(id);
  
    if (ramoData.isPresent()) {
      Ramo _ramo  = ramoData.get();
      _ramo.setCodigo(ramo.getCodigo());
      _ramo.setNombre(ramo.getNombre());
      _ramo.setSemestre(ramo.getSemestre());
      _ramo.setDescripcion(ramo.getDescripcion());
      return new ResponseEntity<>(ramoRepository.save(_ramo ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/ramos/{id}")
  public ResponseEntity<HttpStatus> deleteRamo(@PathVariable("id") String id) {
    try {

      List<Curso> cursos = new ArrayList<>();
      cursoRepository.findByRamoidContaining(id).forEach(cursos::add);
      
      if (cursos.isEmpty()) {
        carreramoRepository.deleteByRamoid(id);
        ramoRepository.deleteById(id);
      } else {
        for(Curso curso : cursos) {
          quizcurRepository.deleteByCursoid(curso.getId());
          curusuRepository.deleteByCursoid(curso.getId());
          cursoRepository.deleteById(curso.getId());
        }
        carreramoRepository.deleteByRamoid(id);
        ramoRepository.deleteById(id);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}