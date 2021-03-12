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

import com.bezkoder.spring.jwt.mongodb.model.UsuQuiz;
import com.bezkoder.spring.jwt.mongodb.model.User;
import com.bezkoder.spring.jwt.mongodb.repository.UsuQuizRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UsuQuizController {

  @Autowired
  UsuQuizRepository usuquizRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/usuquizs/all")
  public ResponseEntity<List<UsuQuiz>> getAllUsuQuizs() {
    try {
      List<UsuQuiz> usuquiz = new ArrayList<UsuQuiz>();

      usuquizRepository.findAll().forEach(usuquiz::add);
  
      if (usuquiz.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(usuquiz, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/usuquizs/usuquizs-chart/{id}")
  public ResponseEntity<?> countById(@PathVariable("id") String id) {
    try {
      ArrayList<String> datos = new ArrayList<String>();

      List<UsuQuiz> respuestas = new ArrayList<>();
      usuquizRepository.findByQuizidContaining(id).forEach(respuestas::add);

      if (respuestas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        for (UsuQuiz respuesta : respuestas) {
          Optional<User> usuquizData = userRepository.findById(respuesta.getUsuarioid());
          if (usuquizData.isPresent()) {
            User _usuario  = usuquizData.get();
            datos.add(_usuario.getUsername());
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          datos.add(respuesta.getPuntajetotal());
        }
      }
  
      return new ResponseEntity<>(datos, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/usuquizs/add")
  public ResponseEntity<UsuQuiz> createUsuQuiz(@RequestBody UsuQuiz UsuQuiz) {
    try {
      int cont = 0;
      List<UsuQuiz> datos = new ArrayList<>();
      UsuQuiz usuquiz = new UsuQuiz(UsuQuiz.getUsuarioid(), UsuQuiz.getQuizid(), UsuQuiz.getPuntajetotal());

      String quizid = usuquiz.getQuizid();
      String usuarioid = usuquiz.getUsuarioid();
      usuquizRepository.findAll().forEach(datos::add);

      if (datos.isEmpty()) {
        usuquizRepository.save(usuquiz);
      } else {
        for (UsuQuiz dato : datos) {
          String quizid2 = dato.getQuizid();
          String usuarioid2 = dato.getUsuarioid();
          boolean comparacion1 = quizid2.equals(quizid);
          boolean comparacion2 = usuarioid2.equals(usuarioid);
          if (comparacion1 == true){
            if (comparacion2 == true){
              cont = 1;
            }
          }
        }
      }

      if (cont == 0){
        usuquizRepository.save(usuquiz);
        return new ResponseEntity<>(usuquiz, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/usuquizs/{id}")
  public ResponseEntity<UsuQuiz> updateUsuQuiz(@PathVariable("id") String id, @RequestBody UsuQuiz usuquiz) {
    Optional<UsuQuiz> usuquizData = usuquizRepository.findById(id);
  
    if (usuquizData.isPresent()) {
      UsuQuiz _usuquiz  = usuquizData.get();
      _usuquiz .setUsuarioid(usuquiz.getUsuarioid());
      _usuquiz .setQuizid(usuquiz.getQuizid());
      _usuquiz .setPuntajetotal(usuquiz.getPuntajetotal());
      return new ResponseEntity<>(usuquizRepository.save(_usuquiz ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/usuquizs/{id}")
  public ResponseEntity<HttpStatus> deleteUsuQuiz(@PathVariable("id") String id) {
    try {
      usuquizRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}