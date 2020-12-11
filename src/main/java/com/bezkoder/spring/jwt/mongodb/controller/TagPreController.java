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

import com.bezkoder.spring.jwt.mongodb.model.TagPre;
import com.bezkoder.spring.jwt.mongodb.repository.TagPreRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TagPreController {

  @Autowired
  TagPreRepository tagpreRepository;

  @GetMapping("/tagpres/all")
  public ResponseEntity<List<TagPre>> getAllTagPres() {
    try {
      List<TagPre> tagpre = new ArrayList<TagPre>();

      tagpreRepository.findAll().forEach(tagpre::add);
  
      if (tagpre.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(tagpre, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/tagpres/add")
  public ResponseEntity<TagPre> createTagPre(@RequestBody TagPre TagPre) {
    try {
      TagPre tagpre = new TagPre(TagPre.getTagid(), TagPre.getPreguntaid());
		  tagpreRepository.save(tagpre);
      return new ResponseEntity<>(tagpre, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/tagpres/{id}")
  public ResponseEntity<TagPre> updateTagPre(@PathVariable("id") String id, @RequestBody TagPre tagpre) {
    Optional<TagPre> tagpreData = tagpreRepository.findById(id);
  
    if (tagpreData.isPresent()) {
      TagPre _tagpre  = tagpreData.get();
      _tagpre .setTagid(tagpre.getTagid());
      _tagpre .setPreguntaid(tagpre.getPreguntaid());
      return new ResponseEntity<>(tagpreRepository.save(_tagpre ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/tagpres/{id}")
  public ResponseEntity<HttpStatus> deleteTagPre(@PathVariable("id") String id) {
    try {
      tagpreRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}