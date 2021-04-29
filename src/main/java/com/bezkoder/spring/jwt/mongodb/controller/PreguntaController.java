package com.bezkoder.spring.jwt.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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

import com.bezkoder.spring.jwt.mongodb.model.Pregunta;
import com.bezkoder.spring.jwt.mongodb.model.TagPre;
import com.bezkoder.spring.jwt.mongodb.model.Respuesta;
import com.bezkoder.spring.jwt.mongodb.model.Retroalimentacion;
import com.bezkoder.spring.jwt.mongodb.model.PreRecur;
import com.bezkoder.spring.jwt.mongodb.model.QuizPre;
import com.bezkoder.spring.jwt.mongodb.model.User;
import com.bezkoder.spring.jwt.mongodb.repository.PreguntaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TagPreRepository;
import com.bezkoder.spring.jwt.mongodb.repository.QuizPreRepository;
import com.bezkoder.spring.jwt.mongodb.repository.PreRecurRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RespuestaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RetroalimentacionRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PreguntaController {

  @Autowired
  PreguntaRepository preguntaRepository;

  @Autowired
  TagPreRepository tagpreRepository;

  @Autowired
  QuizPreRepository quizpreRepository;

  @Autowired
  PreRecurRepository prerecurRepository;

  @Autowired
  RespuestaRepository respuestaRepository;

  @Autowired
  RetroalimentacionRepository retroalimentacionRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/preguntas/all")
  public ResponseEntity<List<Pregunta>> getAllPreguntas(@RequestParam(required = false) String titulo) {
    try {
      List<Pregunta> preguntas = new ArrayList<Pregunta>();
  
      if (titulo == null)
        preguntaRepository.findAll().forEach(preguntas::add);
      else
        preguntaRepository.findByTituloContaining(titulo).forEach(preguntas::add);
  
      if (preguntas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(preguntas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @GetMapping("/preguntas/preguntas-chart/{id}")
  public ResponseEntity<?> countById(@PathVariable("id") String id) {
    try {
      ArrayList<String> datos = new ArrayList<String>();

      List<QuizPre> quizpres = new ArrayList<>();
      quizpreRepository.findByPreguntaidContaining(id).forEach(quizpres::add);

      List<Respuesta> respuestas = new ArrayList<>();
      respuestaRepository.findByPreguntaidContaining(id).forEach(respuestas::add);

      List<Retroalimentacion> retroalimentacions = new ArrayList<>();
      retroalimentacionRepository.findByPreguntaidContaining(id).forEach(retroalimentacions::add);

      List<PreRecur> prerecurs = new ArrayList<>();
      prerecurRepository.findByPreguntaidContaining(id).forEach(prerecurs::add);

      List<TagPre> tagpres = new ArrayList<>();
      tagpreRepository.findByPreguntaidContaining(id).forEach(tagpres::add);

      int tamanio1 = quizpres.size();
      String cantquiz = String.valueOf(tamanio1);
      datos.add("Quiz");
      datos.add(cantquiz);

      int tamanio2 = respuestas.size();
      String cantres = String.valueOf(tamanio2);
      datos.add("Respuesta");
      datos.add(cantres);

      int tamanio3 = retroalimentacions.size();
      String cantretro = String.valueOf(tamanio3);
      datos.add("Retroalimentacion");
      datos.add(cantretro);

      int tamanio4 = prerecurs.size();
      String cantrecur = String.valueOf(tamanio4);
      datos.add("Recurso");
      datos.add(cantrecur);

      int tamanio5 = tagpres.size();
      String canttag = String.valueOf(tamanio5);
      datos.add("Tag");
      datos.add(canttag);
  
      return new ResponseEntity<>(datos, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/preguntas/preguntas-users")
  public ResponseEntity<?> countBy() {
    try {
      ArrayList<String> datos = new ArrayList<String>();

      List<User> users = new ArrayList<>();
      userRepository.findAll().forEach(users::add);

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        for (User user : users) {
					List<Pregunta> preguntas = new ArrayList<>();
					preguntaRepository.findByUsersContaining(user.getId()).forEach(preguntas::add);

          int total = preguntas.size();
          String tamaño = String.valueOf(total);

          String zero = "0";
					boolean comparacion = tamaño.equals(zero);

          if (comparacion == false){
            String nombre = user.getUsername();
            datos.add(nombre);
            datos.add(tamaño);
          }
				}
      }
  
      return new ResponseEntity<>(datos, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/preguntas/{id}")
  public ResponseEntity<Pregunta> getPreguntaById(@PathVariable("id") String id) {
    Optional<Pregunta> preguntaData = preguntaRepository.findById(id);
  
    if (preguntaData.isPresent()) {
      return new ResponseEntity<>(preguntaData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/preguntas/add")
  public ResponseEntity<Pregunta> createPregunta(@RequestBody Pregunta pregunta) {
    try {
      Pregunta _pregunta = preguntaRepository.save(new Pregunta(pregunta.getTitulo(), 
      pregunta.getTipo(), pregunta.getEnunciado(), pregunta.getSubenunciado1(), pregunta.getSubenunciado2(), pregunta.getSubenunciado3(), 
      pregunta.getSubenunciado4(), pregunta.getTemplate(), pregunta.getOpcion1(), pregunta.getOpcion2(), pregunta.getOpcion3(), 
      pregunta.getOpcion4(), pregunta.getRespuesta1(), pregunta.getRespuesta2(), pregunta.getRespuesta3(), pregunta.getRespuesta4(), 
      pregunta.getTiempoRespuesta(), pregunta.getPuntaje(), pregunta.getRandom(), pregunta.getPrivado(), pregunta.getUser()));
      return new ResponseEntity<>(_pregunta, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/preguntas/{id}")
  public ResponseEntity<Pregunta> updatePregunta(@PathVariable("id") String id, @RequestBody Pregunta pregunta) {
    Optional<Pregunta> preguntaData = preguntaRepository.findById(id);
  
    if (preguntaData.isPresent()) {
      Pregunta _pregunta  = preguntaData.get();
      _pregunta.setTitulo(pregunta.getTitulo());
      _pregunta.setTipo(pregunta.getTipo());
      _pregunta.setEnunciado(pregunta.getEnunciado());
      _pregunta.setSubenunciado1(pregunta.getSubenunciado1());
      _pregunta.setSubenunciado2(pregunta.getSubenunciado2());
      _pregunta.setSubenunciado3(pregunta.getSubenunciado3());
      _pregunta.setSubenunciado4(pregunta.getSubenunciado4());
      _pregunta.setTemplate(pregunta.getTemplate());
      _pregunta.setOpcion1(pregunta.getOpcion1());
      _pregunta.setOpcion2(pregunta.getOpcion2());
      _pregunta.setOpcion3(pregunta.getOpcion3());
      _pregunta.setOpcion4(pregunta.getOpcion4());
      _pregunta.setRespuesta1(pregunta.getRespuesta1());
      _pregunta.setRespuesta2(pregunta.getRespuesta2());
      _pregunta.setRespuesta3(pregunta.getRespuesta3());
      _pregunta.setRespuesta4(pregunta.getRespuesta4());
      _pregunta.setTiempoRespuesta(pregunta.getTiempoRespuesta());
      _pregunta.setPuntaje(pregunta.getPuntaje());
      _pregunta.setRandom(pregunta.getRandom());
      _pregunta.setPrivado(pregunta.getPrivado());
      _pregunta.setUser(pregunta.getUser());
      return new ResponseEntity<>(preguntaRepository.save(_pregunta ), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/preguntas/{id}")
  public ResponseEntity<HttpStatus> deletePregunta(@PathVariable("id") String id) {
    try {
      tagpreRepository.deleteByPreguntaid(id);
      quizpreRepository.deleteByPreguntaid(id);
      prerecurRepository.deleteByPreguntaid(id);
      respuestaRepository.deleteByPreguntaid(id);
      retroalimentacionRepository.deleteByPreguntaid(id);
      preguntaRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

