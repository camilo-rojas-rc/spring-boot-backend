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

import com.bezkoder.spring.jwt.mongodb.model.Pregunta;
import com.bezkoder.spring.jwt.mongodb.model.TagPre;
import com.bezkoder.spring.jwt.mongodb.repository.PreguntaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TagPreRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PreguntaController {

  @Autowired
  PreguntaRepository preguntaRepository;

  @Autowired
  TagPreRepository tagpreRepository;

  @GetMapping("/preguntas/all")
  public ResponseEntity<List<Pregunta>> getAllPreguntas(@RequestParam(required = false) String titulo) {
    try {
      List<Pregunta> preguntas = new ArrayList<Pregunta>();
  
      if (titulo == null)
        preguntaRepository.findAll().forEach(preguntas::add);
      else
        preguntaRepository.findByTituloContaining(titulo).forEach(preguntas::add);
  
      if (preguntas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(preguntas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/preguntas/{id}")
  public ResponseEntity<Pregunta> getPreguntaById(@PathVariable("id") String id) {
    Optional<Pregunta> preguntaData = preguntaRepository.findById(id);
  
    if (preguntaData.isPresent()) {
      return new ResponseEntity<>(preguntaData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/preguntas/add")
  public ResponseEntity<Pregunta> createPregunta(@RequestBody Pregunta pregunta) {
    try {
      Pregunta _pregunta = preguntaRepository.save(new Pregunta(pregunta.getTitulo(), 
      pregunta.getTipo(), pregunta.getEnunciado(), pregunta.getTiempoRespuesta(),
      pregunta.getPuntaje(), pregunta.getRandom(), pregunta.getUser()));
      return new ResponseEntity<>(_pregunta, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/preguntas/{id}")
  public ResponseEntity<Pregunta> updatePregunta(@PathVariable("id") String id, @RequestBody Pregunta pregunta) {
    Optional<Pregunta> preguntaData = preguntaRepository.findById(id);
  
    if (preguntaData.isPresent()) {
      Pregunta _pregunta  = preguntaData.get();
      _pregunta.setTitulo(pregunta.getTitulo());
      _pregunta.setTipo(pregunta.getTipo());
      _pregunta.setEnunciado(pregunta.getEnunciado());
      _pregunta.setTiempoRespuesta(pregunta.getTiempoRespuesta());
      _pregunta.setPuntaje(pregunta.getPuntaje());
      _pregunta.setRandom(pregunta.getRandom());
      _pregunta.setUser(pregunta.getUser());
      return new ResponseEntity<>(preguntaRepository.save(_pregunta ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/preguntas/{id}")
  public ResponseEntity<TagPre> deletePregunta(@PathVariable("id") String id) {
    try {
      Optional<TagPre> tagpreData = tagpreRepository.findByPreguntaid(id);
      if (tagpreData.isPresent()) {
        TagPre _tagpre  = tagpreData.get();
        String tagid = _tagpre.getId();
        tagpreRepository.deleteById(tagid);
        preguntaRepository.deleteById(id);
      } else {
        preguntaRepository.deleteById(id);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

