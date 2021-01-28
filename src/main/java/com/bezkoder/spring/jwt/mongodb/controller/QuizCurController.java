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

import com.bezkoder.spring.jwt.mongodb.model.QuizCur;
import com.bezkoder.spring.jwt.mongodb.repository.QuizCurRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class QuizCurController {

  @Autowired
  QuizCurRepository quizcurRepository;

  @GetMapping("/quizcurs/all")
  public ResponseEntity<List<QuizCur>> getAllQuizCurs() {
    try {
      List<QuizCur> quizcur = new ArrayList<QuizCur>();

      quizcurRepository.findAll().forEach(quizcur::add);
  
      if (quizcur.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(quizcur, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/quizcurs/add")
  public ResponseEntity<QuizCur> createQuizCur(@RequestBody QuizCur QuizCur) {
    try {
      int cont = 0;
      List<QuizCur> datos = new ArrayList<>();
      QuizCur quizcur = new QuizCur(QuizCur.getQuizid(), QuizCur.getCursoid());

      String cursoid = quizcur.getCursoid();
      String quizid = quizcur.getQuizid();
      quizcurRepository.findAll().forEach(datos::add);

      if (datos.isEmpty()) {
        quizcurRepository.save(quizcur);
      } else {
        for (QuizCur dato : datos) {
          String cursoid2 = dato.getCursoid();
          String quizid2 = dato.getQuizid();
          boolean comparacion1 = cursoid2.equals(cursoid);
          boolean comparacion2 = quizid2.equals(quizid);
          if (comparacion1 == true){
            if (comparacion2 == true){
              cont = 1;
            }
          }
        }
      }

      if (cont == 0){
        quizcurRepository.save(quizcur);
        return new ResponseEntity<>(quizcur, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/quizcurs/{id}")
  public ResponseEntity<QuizCur> updateQuizCur(@PathVariable("id") String id, @RequestBody QuizCur quizcur) {
    Optional<QuizCur> quizcurData = quizcurRepository.findById(id);
  
    if (quizcurData.isPresent()) {
      QuizCur _quizcur  = quizcurData.get();
      _quizcur .setQuizid(quizcur.getQuizid());
      _quizcur .setCursoid(quizcur.getCursoid());
      return new ResponseEntity<>(quizcurRepository.save(_quizcur ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/quizcurs/{id}")
  public ResponseEntity<HttpStatus> deleteQuizCur(@PathVariable("id") String id) {
    try {
      quizcurRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}