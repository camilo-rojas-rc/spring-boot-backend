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

import com.bezkoder.spring.jwt.mongodb.model.Tag;
import com.bezkoder.spring.jwt.mongodb.model.TagPre;
import com.bezkoder.spring.jwt.mongodb.repository.TagRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TagPreRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TagController {

  @Autowired
  TagRepository tagRepository;

  @Autowired
  TagPreRepository tagpreRepository;

  @GetMapping("/tags/all")
  public ResponseEntity<List<Tag>> getAllTags(@RequestParam(required = false) String nombre) {
    try {
      List<Tag> tags = new ArrayList<Tag>();
  
      if (nombre == null)
        tagRepository.findAll().forEach(tags::add);
      else
        tagRepository.findByNombreContaining(nombre).forEach(tags::add);
  
      if (tags.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(tags, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/tags/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable("id") String id) {
    Optional<Tag> tagData = tagRepository.findById(id);
  
    if (tagData.isPresent()) {
      return new ResponseEntity<>(tagData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/tags/add")
  public ResponseEntity<Tag> createTag(@RequestBody Tag Tag) {
    try {
      Tag tag = new Tag(Tag.getNombre());
	    tagRepository.save(tag);
      return new ResponseEntity<>(tag, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/tags/{id}")
  public ResponseEntity<Tag> updateTag(@PathVariable("id") String id, @RequestBody Tag tag) {
    Optional<Tag> tagData = tagRepository.findById(id);
  
    if (tagData.isPresent()) {
      Tag _tag  = tagData.get();
      _tag .setNombre(tag.getNombre());
      return new ResponseEntity<>(tagRepository.save(_tag ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/tags/{id}")
  public ResponseEntity<TagPre> deleteTag(@PathVariable("id") String id) {
    try {
      Optional<TagPre> tagpreData = tagpreRepository.findByTagid(id);
      if (tagpreData.isPresent()) {
        TagPre _tagpre  = tagpreData.get();
        String tagid = _tagpre.getId();
        tagpreRepository.deleteById(tagid);
        tagRepository.deleteById(id);
      } else {
        tagRepository.deleteById(id);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}