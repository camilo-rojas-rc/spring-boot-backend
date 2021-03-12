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

import com.bezkoder.spring.jwt.mongodb.model.CarreUsu;
import com.bezkoder.spring.jwt.mongodb.repository.CarreUsuRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CarreUsuController {

  @Autowired
  CarreUsuRepository carreusuRepository;

  @GetMapping("/carreusus/all")
  public ResponseEntity<List<CarreUsu>> getAllCarreUsus() {
    try {
      List<CarreUsu> carreusu = new ArrayList<CarreUsu>();

      carreusuRepository.findAll().forEach(carreusu::add);
  
      if (carreusu.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(carreusu, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/carreusus/add")
  public ResponseEntity<CarreUsu> createCarreUsu(@RequestBody CarreUsu CarreUsu) {
    try {
      int cont = 0;
      List<CarreUsu> datos = new ArrayList<>();
      CarreUsu carreusu = new CarreUsu(CarreUsu.getCarreraid(), CarreUsu.getUsuarioid());

      String carreraid = carreusu.getCarreraid();
      String usuarioid = carreusu.getUsuarioid();
      carreusuRepository.findAll().forEach(datos::add);

      if (datos.isEmpty()) {
        carreusuRepository.save(carreusu);
      } else {
        for (CarreUsu dato : datos) {
          String carreraid2 = dato.getCarreraid();
          String usuarioid2 = dato.getUsuarioid();
          boolean comparacion1 = carreraid2.equals(carreraid);
          boolean comparacion2 = usuarioid2.equals(usuarioid);
          if (comparacion1 == true){
            if (comparacion2 == true){
              cont = 1;
            }
          }
        }
      }

      if (cont == 0){
        carreusuRepository.save(carreusu);
        return new ResponseEntity<>(carreusu, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/carreusus/{id}")
  public ResponseEntity<CarreUsu> updateCarreUsu(@PathVariable("id") String id, @RequestBody CarreUsu carreusu) {
    Optional<CarreUsu> carreusuData = carreusuRepository.findById(id);
  
    if (carreusuData.isPresent()) {
      CarreUsu _carreusu  = carreusuData.get();
      _carreusu .setCarreraid(carreusu.getCarreraid());
      _carreusu .setUsuarioid(carreusu.getUsuarioid());
      return new ResponseEntity<>(carreusuRepository.save(_carreusu ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/carreusus/{id}")
  public ResponseEntity<HttpStatus> deleteCarreUsu(@PathVariable("id") String id) {
    try {
      carreusuRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}