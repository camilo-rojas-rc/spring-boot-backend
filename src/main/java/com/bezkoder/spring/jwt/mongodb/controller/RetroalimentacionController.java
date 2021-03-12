package com.bezkoder.spring.jwt.mongodb.controller;

import java.io.IOException;
import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;

import com.bezkoder.spring.jwt.mongodb.model.Retroalimentacion;
import com.bezkoder.spring.jwt.mongodb.repository.RetroalimentacionRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RetroalimentacionController {

  @Autowired
  RetroalimentacionRepository retroalimentacionRepository;

  @GetMapping("/retroalimentacions/all")
  public ResponseEntity<List<Retroalimentacion>> getAllRetroalimentacions(@RequestParam(required = false) String enunciado) {
    try {
      List<Retroalimentacion> retroalimentacions = new ArrayList<Retroalimentacion>();
  
      if (enunciado == null)
        retroalimentacionRepository.findAll().forEach(retroalimentacions::add);
      else
        retroalimentacionRepository.findByEnunciadoContaining(enunciado).forEach(retroalimentacions::add);
  
      if (retroalimentacions.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(retroalimentacions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/retroalimentacions/{id}")
  public ResponseEntity<Retroalimentacion> getRetroalimentacionById(@PathVariable("id") String id) {
    Optional<Retroalimentacion> retroalimentacionData = retroalimentacionRepository.findById(id);
  
    if (retroalimentacionData.isPresent()) {
      return new ResponseEntity<>(retroalimentacionData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/retroalimentacions/add")
  public ResponseEntity<Retroalimentacion> createRetroalimentacion(@RequestBody Retroalimentacion Retroalimentacion) {
    try {
      Retroalimentacion retroalimentacion = new Retroalimentacion(Retroalimentacion.getEnunciado(), Retroalimentacion.getActivo(), Retroalimentacion.getPreguntaid());
	    retroalimentacionRepository.save(retroalimentacion);
      return new ResponseEntity<>(retroalimentacion, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/retroalimentacions/{id}")
  public ResponseEntity<Retroalimentacion> updateRetroalimentacion(@PathVariable("id") String id, @RequestBody Retroalimentacion retroalimentacion) {
    Optional<Retroalimentacion> retroalimentacionData = retroalimentacionRepository.findById(id);
  
    if (retroalimentacionData.isPresent()) {
      Retroalimentacion _retroalimentacion  = retroalimentacionData.get();
      _retroalimentacion .setEnunciado(retroalimentacion.getEnunciado());
      _retroalimentacion .setActivo(retroalimentacion.getActivo());
      _retroalimentacion .setPreguntaid(retroalimentacion.getPreguntaid());
      return new ResponseEntity<>(retroalimentacionRepository.save(_retroalimentacion ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/retroalimentacions/{id}")
  public ResponseEntity<HttpStatus> deleteRetroalimentacion(@PathVariable("id") String id) {
    try {
      retroalimentacionRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}