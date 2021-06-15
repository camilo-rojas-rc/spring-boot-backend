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

import com.bezkoder.spring.jwt.mongodb.model.Recurso;
import com.bezkoder.spring.jwt.mongodb.model.PreRecur;
import com.bezkoder.spring.jwt.mongodb.security.services.RecursoService;
import com.bezkoder.spring.jwt.mongodb.repository.RecursoRepository;
import com.bezkoder.spring.jwt.mongodb.repository.PreRecurRepository;
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
    
    @Autowired
	  PreRecurRepository prerecurRepository;

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
    public ResponseEntity<Recurso> addRecurso(@RequestParam("title") String title, @RequestParam("type") String type, @RequestParam(required = false)  String inicialmin, @RequestParam(required = false) String finalmin, @RequestParam(required = false) String link, @RequestParam("privado") boolean privado, @RequestParam("users") String users, @RequestParam(required = false) MultipartFile resource) throws IOException {
      try {
        Recurso _recurso;
        
        if (link != null) {
          link = link.substring(17);

          _recurso = new Recurso(title, type, inicialmin, finalmin, link, privado, users);

          recursoRepository.save(_recurso);
        } else {
          _recurso = new Recurso(title, type, inicialmin, finalmin, link, privado, users);
          _recurso.setRecurso(new Binary(BsonBinarySubType.BINARY, resource.getBytes()));

          recursoRepository.save(_recurso);
        }
        return new ResponseEntity<>(_recurso, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/recursos/create")
    public ResponseEntity<?> createRecurso(@RequestParam(required = false) String id) {
      try {
        String idrecurso = id.substring(0, 24);
        String idpregunta = id.substring(24, 48);
        String iduser = id.substring(48, id.length());

        Optional<Recurso> recursoData = recursoRepository.findById(idrecurso);
        
        if (recursoData.isPresent()) {
          Recurso _recurso  = recursoData.get();

          Recurso recurso  = new Recurso(_recurso.getTitle(), _recurso.getType(), _recurso.getInicialmin(),
          _recurso.getFinalmin(), _recurso.getLink(), _recurso.getPrivado(), iduser);

          recurso.setRecurso(_recurso.getRecurso());

          recursoRepository.save(recurso);

          int cont = 0;
          List<PreRecur> datos = new ArrayList<>();

          String recursosid = recurso.getId();

          PreRecur prerecur = new PreRecur(idpregunta, recursosid);

          String preguntaid = prerecur.getPreguntaid();
          String recursoid = prerecur.getRecursoid();
          prerecurRepository.findAll().forEach(datos::add);

          if (datos.isEmpty()) {
            prerecurRepository.save(prerecur);
          } else {
            for (PreRecur dato : datos) {
              String preguntaid2 = dato.getPreguntaid();
              String recursoid2 = dato.getRecursoid();
              boolean comparacion1 = preguntaid2.equals(preguntaid);
              boolean comparacion2 = recursoid2.equals(recursoid);
              if (comparacion1 == true){
                if (comparacion2 == true){
                  cont = 1;
                }
              }
            }
          }

          if (cont == 0){
            prerecurRepository.save(prerecur);
            return new ResponseEntity<>(prerecur, HttpStatus.CREATED);

          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }

        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/recursos/{id}")
    public ResponseEntity<Recurso> updateRecurso(@PathVariable("id") String id, @RequestBody Recurso recurso) {
      Optional<Recurso> recursoData = recursoRepository.findById(id);
    
      if (recursoData.isPresent()) {
        Recurso _recurso  = recursoData.get();
        _recurso .setTitle(recurso.getTitle());
        _recurso .setType(recurso.getType());
        _recurso .setInicialmin(recurso.getInicialmin());
        _recurso .setFinalmin(recurso.getFinalmin());
        _recurso .setLink(recurso.getLink());
        _recurso .setPrivado(recurso.getPrivado());
        _recurso .setUser(recurso.getUser());
        return new ResponseEntity<>(recursoRepository.save(_recurso ), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/recursos/resource/{id}")
    public ResponseEntity<Resource> getRecurso(@PathVariable String id) {
        Recurso recurso = recursoService.getRecurso(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getTitle() + "\"").body(new ByteArrayResource(recurso.getRecurso().getData()));
    }

    @GetMapping("/recursos/{id}")
    public ResponseEntity<Recurso> getRecursoById(@PathVariable("id") String id) {
      Optional<Recurso> recursoData = recursoRepository.findById(id);
    
      if (recursoData.isPresent()) {
        return new ResponseEntity<>(recursoData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/recursos/{id}")
    public ResponseEntity<HttpStatus> deleteRecurso(@PathVariable("id") String id) {
      try {
        prerecurRepository.deleteByRecursoid(id);
        recursoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
