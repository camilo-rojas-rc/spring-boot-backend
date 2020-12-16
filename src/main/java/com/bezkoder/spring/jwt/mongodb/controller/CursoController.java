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

import com.bezkoder.spring.jwt.mongodb.model.Curso;
import com.bezkoder.spring.jwt.mongodb.repository.CursoRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CurUsuRepository;
import com.bezkoder.spring.jwt.mongodb.repository.QuizCurRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CursoController {

  @Autowired
  CursoRepository cursoRepository;

  @Autowired
  QuizCurRepository quizcurRepository;

  @Autowired
  CurUsuRepository curusuRepository;

  @GetMapping("/cursos/all")
  public ResponseEntity<List<Curso>> getAllCursos(@RequestParam(required = false) String codigo) {
    try {
      List<Curso> cursos = new ArrayList<Curso>();
  
      if (codigo == null)
        cursoRepository.findAll().forEach(cursos::add);
      else
        cursoRepository.findByCodigoContaining(codigo).forEach(cursos::add);
  
      if (cursos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(cursos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/cursos/{id}")
  public ResponseEntity<Curso> getCursoById(@PathVariable("id") String id) {
    Optional<Curso> cursoData = cursoRepository.findById(id);
  
    if (cursoData.isPresent()) {
      return new ResponseEntity<>(cursoData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/cursos/add")
  public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
    try {
      Curso _curso = cursoRepository.save(new Curso(curso.getCodigo(), 
      curso.getSemestre(), curso.getAño(), curso.getDescripcion(),
      curso.getPassword(), curso.getActivo(), curso.getRamoid()));
      return new ResponseEntity<>(_curso, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/cursos/{id}")
  public ResponseEntity<Curso> updateCurso(@PathVariable("id") String id, @RequestBody Curso curso) {
    Optional<Curso> cursoData = cursoRepository.findById(id);
  
    if (cursoData.isPresent()) {
      Curso _curso  = cursoData.get();
      _curso.setCodigo(curso.getCodigo());
      _curso.setSemestre(curso.getSemestre());
      _curso.setAño(curso.getAño());
      _curso.setDescripcion(curso.getDescripcion());
      _curso.setPassword(curso.getPassword());
      _curso.setActivo(curso.getActivo());
      _curso.setRamoid(curso.getRamoid());
      return new ResponseEntity<>(cursoRepository.save(_curso ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/cursos/{id}")
  public ResponseEntity<HttpStatus> deleteCurso(@PathVariable("id") String id) {
    try {
      quizcurRepository.deleteByCursoid(id);
      curusuRepository.deleteByCursoid(id);
      cursoRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}