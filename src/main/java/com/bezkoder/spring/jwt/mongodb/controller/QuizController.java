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

import com.bezkoder.spring.jwt.mongodb.model.Quiz;
import com.bezkoder.spring.jwt.mongodb.repository.QuizRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class QuizController {

  @Autowired
  QuizRepository quizRepository;

  @GetMapping("/quizs/all")
  public ResponseEntity<List<Quiz>> getAllQuizs(@RequestParam(required = false) String titulo) {
    try {
      List<Quiz> quizs = new ArrayList<Quiz>();
  
      if (titulo == null)
          quizRepository.findAll().forEach(quizs::add);
      else
          quizRepository.findByTituloContaining(titulo).forEach(quizs::add);
  
      if (quizs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(quizs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/quizs/{id}")
  public ResponseEntity<Quiz> getQuizById(@PathVariable("id") String id) {
    Optional<Quiz> quizData = quizRepository.findById(id);
  
    if (quizData.isPresent()) {
      return new ResponseEntity<>(quizData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/quizs/add")
  public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
    try {
      Quiz _quiz = quizRepository.save(new Quiz(quiz.getTitulo(), quiz.getDescripcion(), 
      quiz.getActivo(), quiz.getTiempodisponible(), quiz.getRandom(), quiz.getFechacreacion(),
      quiz.getFechatermino()));
      return new ResponseEntity<>(_quiz, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/quizs/{id}")
  public ResponseEntity<Quiz> updateQuiz(@Valid @PathVariable("id") String id, @RequestBody Quiz quiz) {
    Optional<Quiz> quizData = quizRepository.findById(id);
  
    if (quizData.isPresent()) {
      Quiz _quiz  = quizData.get();
      _quiz.setTitulo(quiz.getTitulo());
      _quiz.setDescripcion(quiz.getDescripcion());
      _quiz.setActivo(quiz.getActivo());
      _quiz.setTiempodisponible(quiz.getTiempodisponible());
      _quiz.setRandom(quiz.getRandom());
      _quiz.setFechacreacion(quiz.getFechacreacion());
      _quiz.setFechatermino(quiz.getFechatermino());
      return new ResponseEntity<>(quizRepository.save(_quiz ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/quizs/{id}")
  public ResponseEntity<HttpStatus> deleteQuiz(@PathVariable("id") String id) {
    try {
      quizRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}