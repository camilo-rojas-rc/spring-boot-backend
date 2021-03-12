package com.bezkoder.spring.jwt.mongodb.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.bezkoder.spring.jwt.mongodb.model.Perfil;
import com.bezkoder.spring.jwt.mongodb.security.services.PerfilService;
import com.bezkoder.spring.jwt.mongodb.repository.PerfilRepository;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    PerfilRepository perfilRepository;

    @GetMapping("/perfils/all")
    public ResponseEntity<List<Perfil>> getAllPerfils() {
      try {
      List<Perfil> perfils = new ArrayList<Perfil>();
      perfilRepository.findAll().forEach(perfils::add);
    
      if (perfils.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    
      return new ResponseEntity<>(perfils, HttpStatus.OK);
      } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/perfils/add")
    public ResponseEntity<Perfil> addPerfil(@RequestParam("activo") boolean activo, @RequestParam("users") String users, @RequestParam("resource") MultipartFile resource) throws IOException {
      try {
        Perfil perfil = new Perfil(activo, users);
        perfil.setRecurso(new Binary(BsonBinarySubType.BINARY, resource.getBytes()));
        perfil = perfilRepository.insert(perfil);
        return new ResponseEntity<>(perfil, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/perfils/{id}")
    public ResponseEntity<Perfil> updatePerfil(@PathVariable("id") String id, @RequestBody Perfil perfil) {
      Optional<Perfil> perfilData = perfilRepository.findById(id);
    
      if (perfilData.isPresent()) {
        Perfil _perfil  = perfilData.get();
        _perfil .setActivo(perfil.getActivo());
        _perfil .setUser(perfil.getUser());
        return new ResponseEntity<>(perfilRepository.save(_perfil ), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/perfils/resource/{id}")
    public ResponseEntity<Resource> getPerfil(@PathVariable String id) {
        Perfil perfil = perfilService.getPerfil(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + perfil.getId() + "\"").body(new ByteArrayResource(perfil.getRecurso().getData()));
    }

    @GetMapping("/perfils/{id}")
    public ResponseEntity<Perfil> getPerfilById(@PathVariable("id") String id) {
      Optional<Perfil> perfilData = perfilRepository.findById(id);
    
      if (perfilData.isPresent()) {
        return new ResponseEntity<>(perfilData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/perfils/{id}")
    public ResponseEntity<HttpStatus> deletePerfil(@PathVariable("id") String id) {
      try {
        perfilRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}