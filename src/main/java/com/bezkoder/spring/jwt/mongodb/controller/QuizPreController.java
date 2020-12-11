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

import com.bezkoder.spring.jwt.mongodb.model.QuizPre;
import com.bezkoder.spring.jwt.mongodb.repository.QuizPreRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class QuizPreController {

  @Autowired
  QuizPreRepository quizpreRepository;

  @GetMapping("/quizpres/all")
  public ResponseEntity<List<QuizPre>> getAllQuizPres() {
    try {
      List<QuizPre> quizpre = new ArrayList<QuizPre>();

      quizpreRepository.findAll().forEach(quizpre::add);
  
      if (quizpre.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(quizpre, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/quizpres/add")
  public ResponseEntity<QuizPre> createQuizPre(@RequestBody QuizPre QuizPre) {
    try {
      QuizPre quizpre = new QuizPre(QuizPre.getQuizid(), QuizPre.getPreguntaid());
		  quizpreRepository.save(quizpre);
      return new ResponseEntity<>(quizpre, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/quizpres/{id}")
  public ResponseEntity<QuizPre> updateQuizPre(@PathVariable("id") String id, @RequestBody QuizPre quizpre) {
    Optional<QuizPre> quizpreData = quizpreRepository.findById(id);
  
    if (quizpreData.isPresent()) {
      QuizPre _quizpre  = quizpreData.get();
      _quizpre .setQuizid(quizpre.getQuizid());
      _quizpre .setPreguntaid(quizpre.getPreguntaid());
      return new ResponseEntity<>(quizpreRepository.save(_quizpre ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/quizpres/{id}")
  public ResponseEntity<HttpStatus> deleteQuizPre(@PathVariable("id") String id) {
    try {
      quizpreRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}