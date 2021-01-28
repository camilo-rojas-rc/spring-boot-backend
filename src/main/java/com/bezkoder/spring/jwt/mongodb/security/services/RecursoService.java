package com.bezkoder.spring.jwt.mongodb.security.services;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import com.bezkoder.spring.jwt.mongodb.repository.RecursoRepository;
import com.bezkoder.spring.jwt.mongodb.model.Recurso;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepo;

    public Recurso getRecurso(String id) {
        return recursoRepo.findById(id).get();
    }

    public String addRecurso(String title, String type, @RequestParam(required = false) String link, boolean privado, String users, MultipartFile file) throws IOException {
        Recurso recurso = new Recurso(title, type, link, privado, users);
        recurso.setRecurso(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        recurso = recursoRepo.insert(recurso);
        return recurso.getId();
    }
}
