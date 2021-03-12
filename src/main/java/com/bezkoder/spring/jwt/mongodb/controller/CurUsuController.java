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

import com.bezkoder.spring.jwt.mongodb.model.CurUsu;
import com.bezkoder.spring.jwt.mongodb.repository.CurUsuRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CurUsuController {

  @Autowired
  CurUsuRepository curusuRepository;

  @GetMapping("/curusus/all")
  public ResponseEntity<List<CurUsu>> getAllCurUsus() {
    try {
      List<CurUsu> curusu = new ArrayList<CurUsu>();

      curusuRepository.findAll().forEach(curusu::add);
  
      if (curusu.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(curusu, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/curusus/add")
  public ResponseEntity<CurUsu> createCurUsu(@RequestBody CurUsu CurUsu) {
    try {
      int cont = 0;
      List<CurUsu> datos = new ArrayList<>();
      CurUsu curusu = new CurUsu(CurUsu.getCursoid(), CurUsu.getUsuarioid());

      String cursoid = curusu.getCursoid();
      String usuarioid = curusu.getUsuarioid();
      curusuRepository.findAll().forEach(datos::add);

      if (datos.isEmpty()) {
        curusuRepository.save(curusu);
      } else {
        for (CurUsu dato : datos) {
          String cursoid2 = dato.getCursoid();
          String usuarioid2 = dato.getUsuarioid();
          boolean comparacion1 = cursoid2.equals(cursoid);
          boolean comparacion2 = usuarioid2.equals(usuarioid);
          if (comparacion1 == true){
            if (comparacion2 == true){
              cont = 1;
            }
          }
        }
      }

      if (cont == 0){
        curusuRepository.save(curusu);
        return new ResponseEntity<>(curusu, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/curusus/{id}")
  public ResponseEntity<CurUsu> updateCurUsu(@PathVariable("id") String id, @RequestBody CurUsu curusu) {
    Optional<CurUsu> curusuData = curusuRepository.findById(id);
  
    if (curusuData.isPresent()) {
      CurUsu _curusu  = curusuData.get();
      _curusu .setCursoid(curusu.getCursoid());
      _curusu .setUsuarioid(curusu.getUsuarioid());
      return new ResponseEntity<>(curusuRepository.save(_curusu ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/curusus/{id}")
  public ResponseEntity<HttpStatus> deleteCurUsu(@PathVariable("id") String id) {
    try {
      curusuRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}