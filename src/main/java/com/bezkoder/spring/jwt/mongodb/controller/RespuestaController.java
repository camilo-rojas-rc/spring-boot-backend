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

import com.bezkoder.spring.jwt.mongodb.model.Respuesta;
import com.bezkoder.spring.jwt.mongodb.repository.RespuestaRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RespuestaController {

  @Autowired
  RespuestaRepository respuestaRepository;

  @GetMapping("/respuestas/all")
  public ResponseEntity<List<Respuesta>> getAllRespuestas() {
    try {
      List<Respuesta> respuestas = new ArrayList<Respuesta>();
  
      respuestaRepository.findAll().forEach(respuestas::add);

      if (respuestas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(respuestas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/respuestas/{id}")
  public ResponseEntity<Respuesta> getRespuestaById(@PathVariable("id") String id) {
    Optional<Respuesta> respuestaData = respuestaRepository.findById(id);
  
    if (respuestaData.isPresent()) {
      return new ResponseEntity<>(respuestaData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/respuestas/add")
  public ResponseEntity<Respuesta> createRespuesta(@RequestBody Respuesta Respuesta) {
    try {
      int cont = 0;
      List<Respuesta> datos = new ArrayList<>();
      Respuesta _respuesta = new Respuesta(Respuesta.getTiemporespuesta(),
      Respuesta.getRespuesta1(), Respuesta.getRespuesta2(), Respuesta.getRespuesta3(),
      Respuesta.getRespuesta4(), Respuesta.getRespuesta5(), Respuesta.getPuntaje(),
      Respuesta.getUsuarioid(), Respuesta.getPreguntaid(), Respuesta.getQuizid());
  
      String preguntaid = _respuesta.getPreguntaid();
      String quizid = _respuesta.getQuizid();
      String usuarioid = _respuesta.getUsuarioid();
      respuestaRepository.findAll().forEach(datos::add);
  
      if (datos.isEmpty()) {
        respuestaRepository.save(_respuesta);
      } else {
        for (Respuesta dato : datos) {
          String preguntaid2 = dato.getPreguntaid();
          String quizid2 = dato.getQuizid();
          String usuarioid2 = dato.getUsuarioid();
          boolean comparacion1 = preguntaid2.equals(preguntaid);
          boolean comparacion2 = quizid2.equals(quizid);
          boolean comparacion3 = usuarioid2.equals(usuarioid);
          if (comparacion1 == true){
            if (comparacion2 == true){
              if (comparacion3 == true){
                cont = 1;
              }
            }
          }
        }
      }
  
      if (cont == 0){
        respuestaRepository.save(_respuesta);
        return new ResponseEntity<>(_respuesta, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  } 
  
  @PutMapping("/respuestas/{id}")
  public ResponseEntity<Respuesta> updateRespuesta(@PathVariable("id") String id, @RequestBody Respuesta respuesta) {
    Optional<Respuesta> respuestaData = respuestaRepository.findById(id);
  
    if (respuestaData.isPresent()) {
      Respuesta _respuesta  = respuestaData.get();
      _respuesta.setTiemporespuesta(respuesta.getTiemporespuesta());
      _respuesta.setRespuesta1(respuesta.getRespuesta1());
      _respuesta.setRespuesta2(respuesta.getRespuesta2());
      _respuesta.setRespuesta3(respuesta.getRespuesta3());
      _respuesta.setRespuesta4(respuesta.getRespuesta4());
      _respuesta.setRespuesta5(respuesta.getRespuesta5());
      _respuesta.setPuntaje(respuesta.getPuntaje());
      _respuesta.setUsuarioid(respuesta.getUsuarioid());
      _respuesta.setPreguntaid(respuesta.getPreguntaid());
      _respuesta.setQuizid(respuesta.getQuizid());
      return new ResponseEntity<>(respuestaRepository.save(_respuesta ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/respuestas/{id}")
  public ResponseEntity<HttpStatus> deleteRespuesta(@PathVariable("id") String id) {
    try {
      respuestaRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}