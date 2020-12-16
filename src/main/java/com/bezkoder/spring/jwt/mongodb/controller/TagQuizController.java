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

import com.bezkoder.spring.jwt.mongodb.model.TagQuiz;
import com.bezkoder.spring.jwt.mongodb.repository.TagQuizRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TagQuizController {

  @Autowired
  TagQuizRepository tagquizRepository;

  @GetMapping("/tagquizs/all")
  public ResponseEntity<List<TagQuiz>> getAllTagQuizs() {
    try {
      List<TagQuiz> tagquiz = new ArrayList<TagQuiz>();

      tagquizRepository.findAll().forEach(tagquiz::add);
  
      if (tagquiz.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(tagquiz, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/tagquizs/add")
  public ResponseEntity<TagQuiz> createTagQuiz(@RequestBody TagQuiz TagQuiz) {
    try {
      TagQuiz tagquiz = new TagQuiz(TagQuiz.getTagid(), TagQuiz.getQuizid());
		  tagquizRepository.save(tagquiz);
      return new ResponseEntity<>(tagquiz, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/tagquizs/{id}")
  public ResponseEntity<TagQuiz> updateTagQuiz(@PathVariable("id") String id, @RequestBody TagQuiz tagquiz) {
    Optional<TagQuiz> tagquizData = tagquizRepository.findById(id);
  
    if (tagquizData.isPresent()) {
      TagQuiz _tagquiz  = tagquizData.get();
      _tagquiz .setTagid(tagquiz.getTagid());
      _tagquiz .setQuizid(tagquiz.getQuizid());
      return new ResponseEntity<>(tagquizRepository.save(_tagquiz ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/tagquizs/{id}")
  public ResponseEntity<HttpStatus> deleteTagQuiz(@PathVariable("id") String id) {
    try {
      tagquizRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}