package com.bezkoder.spring.jwt.mongodb.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.bezkoder.spring.jwt.mongodb.model.Recurso;
import com.bezkoder.spring.jwt.mongodb.security.services.RecursoService;
import com.bezkoder.spring.jwt.mongodb.repository.RecursoRepository;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @Autowired
	  RecursoRepository recursoRepository;

    @GetMapping("/recursos/all")
    public ResponseEntity<List<Recurso>> getAllRecursos(@RequestParam(required = false) String title) {
      try {
      List<Recurso> recursos = new ArrayList<Recurso>();
    
      if (title == null)
        recursoRepository.findAll().forEach(recursos::add);
    
      if (recursos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    
      return new ResponseEntity<>(recursos, HttpStatus.OK);
      } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/recursos/add")
    public ResponseEntity<?> addRecurso(@RequestParam("title") String title, @RequestParam("type") String type, @RequestParam(required = false) String link, @RequestParam("resource") MultipartFile resource) throws IOException {
      try {
        if (link != null) {
          link = link.substring(32);
        }
        
        String recurso = recursoService.addRecurso(title, type, link, resource);
        return new ResponseEntity<>(recurso, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/recursos/{id}")
    public ResponseEntity<Resource> getRecurso(@PathVariable String id) {
        Recurso recurso = recursoService.getRecurso(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getTitle() + "\"").body(new ByteArrayResource(recurso.getRecurso().getData()));
    }

    @DeleteMapping("/recursos/{id}")
    public ResponseEntity<HttpStatus> deleteRecurso(@PathVariable("id") String id) {
      try {
        recursoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
