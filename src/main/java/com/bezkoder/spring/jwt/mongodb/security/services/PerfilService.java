package com.bezkoder.spring.jwt.mongodb.security.services;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import com.bezkoder.spring.jwt.mongodb.repository.PerfilRepository;
import com.bezkoder.spring.jwt.mongodb.model.Perfil;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepo;

    public Perfil getPerfil(String id) {
        return perfilRepo.findById(id).get();
    }
}