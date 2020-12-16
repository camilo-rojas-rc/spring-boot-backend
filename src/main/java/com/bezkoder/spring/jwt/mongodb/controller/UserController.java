package com.bezkoder.spring.jwt.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bezkoder.spring.jwt.mongodb.model.User;
import com.bezkoder.spring.jwt.mongodb.model.Pregunta;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.bezkoder.spring.jwt.mongodb.repository.PreguntaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CarreUsuRepository;
import com.bezkoder.spring.jwt.mongodb.repository.CurUsuRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TagPreRepository;
import com.bezkoder.spring.jwt.mongodb.repository.QuizPreRepository;
import com.bezkoder.spring.jwt.mongodb.repository.PreRecurRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RespuestaRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RetroalimentacionRepository;

import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import com.bezkoder.spring.jwt.mongodb.model.ERole;
import com.bezkoder.spring.jwt.mongodb.model.Role;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
  UserRepository userRepository;
  
  @Autowired
  PreguntaRepository preguntaRepository;
  
  @Autowired
  CarreUsuRepository carreusuRepository;
  
  @Autowired
  CurUsuRepository curusuRepository;

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

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
	  try {
		List<User> users = new ArrayList<User>();
	
		if (username == null)
		  userRepository.findAll().forEach(users::add);
		else
		  userRepository.findByUsernameContaining(username).forEach(users::add);
	
		if (users.isEmpty()) {
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
		return new ResponseEntity<>(users, HttpStatus.OK);
	  } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('TEACHER')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/teacher")
	@PreAuthorize("hasRole('TEACHER')")
	public String adminAccess() {
		return "teacher Board.";
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role adminRole = roleRepository.findByName(ERole.teacher)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(adminRole);
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    try {

      List<Pregunta> preguntas = new ArrayList<>();
      preguntaRepository.findByUsersContaining(id).forEach(preguntas::add);
      
      if (preguntas.isEmpty()) {
        carreusuRepository.deleteByUsuarioid(id);
        curusuRepository.deleteByUsuarioid(id);
        respuestaRepository.deleteByUsuarioid(id);
        userRepository.deleteById(id);
      } else {
        for(Pregunta pregunta : preguntas) {
          tagpreRepository.deleteByPreguntaid(pregunta.getId());
          quizpreRepository.deleteByPreguntaid(pregunta.getId());
          prerecurRepository.deleteByPreguntaid(pregunta.getId());
          respuestaRepository.deleteByPreguntaid(pregunta.getId());
          retroalimentacionRepository.deleteByPreguntaid(pregunta.getId());
          preguntaRepository.deleteById(pregunta.getId());
        }
        carreusuRepository.deleteByUsuarioid(id);
        curusuRepository.deleteByUsuarioid(id);
        respuestaRepository.deleteByUsuarioid(id);
        userRepository.deleteById(id);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}
