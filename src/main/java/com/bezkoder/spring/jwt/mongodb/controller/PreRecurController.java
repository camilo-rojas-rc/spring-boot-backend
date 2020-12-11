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

import com.bezkoder.spring.jwt.mongodb.model.PreRecur;
import com.bezkoder.spring.jwt.mongodb.repository.PreRecurRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PreRecurController {

  @Autowired
  PreRecurRepository prerecurRepository;

  @GetMapping("/prerecurs/all")
  public ResponseEntity<List<PreRecur>> getAllPreRecurs() {
    try {
      List<PreRecur> prerecur = new ArrayList<PreRecur>();

      prerecurRepository.findAll().forEach(prerecur::add);
  
      if (prerecur.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(prerecur, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/prerecurs/add")
  public ResponseEntity<PreRecur> createPreRecur(@RequestBody PreRecur PreRecur) {
    try {
      PreRecur prerecur = new PreRecur(PreRecur.getPreguntaid(), PreRecur.getRecursoid());
		  prerecurRepository.save(prerecur);
      return new ResponseEntity<>(prerecur, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/prerecurs/{id}")
  public ResponseEntity<PreRecur> updatePreRecur(@PathVariable("id") String id, @RequestBody PreRecur prerecur) {
    Optional<PreRecur> prerecurData = prerecurRepository.findById(id);
  
    if (prerecurData.isPresent()) {
      PreRecur _prerecur  = prerecurData.get();
      _prerecur .setRecursoid(prerecur.getRecursoid());
      _prerecur .setPreguntaid(prerecur.getPreguntaid());
      return new ResponseEntity<>(prerecurRepository.save(_prerecur ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/prerecurs/{id}")
  public ResponseEntity<HttpStatus> deletePreRecur(@PathVariable("id") String id) {
    try {
      prerecurRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}