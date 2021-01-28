package com.bezkoder.spring.jwt.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
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

import com.bezkoder.spring.jwt.mongodb.model.QuizPre;
import com.bezkoder.spring.jwt.mongodb.model.Pregunta;
import com.bezkoder.spring.jwt.mongodb.model.Respuesta;
import com.bezkoder.spring.jwt.mongodb.model.Retroalimentacion;
import com.bezkoder.spring.jwt.mongodb.model.PreRecur;
import com.bezkoder.spring.jwt.mongodb.model.TagPre;
import com.bezkoder.spring.jwt.mongodb.repository.QuizPreRepository;
import com.bezkoder.spring.jwt.mongodb.repository.PreguntaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RespuestaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RetroalimentacionRepository;
import com.bezkoder.spring.jwt.mongodb.repository.PreRecurRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TagPreRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class QuizPreController {

  @Autowired
  QuizPreRepository quizpreRepository;

  @Autowired
  PreguntaRepository preguntaRepository;

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

  @GetMapping("/quizpres/quizpre-chart")
  public ResponseEntity<?> countByPrerecur() {
    try {
      ArrayList<String> datos = new ArrayList<String>();
      List<QuizPre> quizpres = new ArrayList<>();
      quizpreRepository.findAll().forEach(quizpres::add);
     
      if (quizpres.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        ArrayList<String> preguntaids = new ArrayList<String>();
  
        for (QuizPre quizpre : quizpres) {
          String pid = quizpre.getPreguntaid();
          preguntaids.add(pid);
        }
        
        Set<String> hashSet = new HashSet<String>(preguntaids);
        preguntaids.clear();
        preguntaids.addAll(hashSet);
  
        for (String preguntaid : preguntaids) {
          List<QuizPre> preguntas = new ArrayList<>();
          quizpreRepository.findByPreguntaidContaining(preguntaid).forEach(preguntas::add);
         
          int tamanio = preguntas.size();
          String cantidad = String.valueOf(tamanio);
          String id = preguntaid;
  
          Optional<Pregunta> preguntaData = preguntaRepository.findById(id);
          if (preguntaData.isPresent()) {
            Pregunta _pregunta  = preguntaData.get();
            datos.add(_pregunta.getTitulo());
            datos.add(cantidad);
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
        }
      }
  
      return new ResponseEntity<>(datos, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
 
  @PostMapping("/quizpres/add")
  public ResponseEntity<QuizPre> createQuizPre(@RequestBody QuizPre QuizPre) {
    try {
      int cont = 0;
      List<QuizPre> datos = new ArrayList<>();
      QuizPre quizpre = new QuizPre(QuizPre.getQuizid(), QuizPre.getPreguntaid());

      String preguntaid = quizpre.getPreguntaid();
      String quizid = quizpre.getQuizid();
      quizpreRepository.findAll().forEach(datos::add);

      if (datos.isEmpty()) {
        quizpreRepository.save(quizpre);
      } else {
        for (QuizPre dato : datos) {
          String preguntaid2 = dato.getPreguntaid();
          String quizid2 = dato.getQuizid();
          boolean comparacion1 = preguntaid2.equals(preguntaid);
          boolean comparacion2 = quizid2.equals(quizid);
          if (comparacion1 == true){
            if (comparacion2 == true){
              cont = 1;
            }
          }
        }
      }

      if (cont == 0){
        quizpreRepository.save(quizpre);
        return new ResponseEntity<>(quizpre, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

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