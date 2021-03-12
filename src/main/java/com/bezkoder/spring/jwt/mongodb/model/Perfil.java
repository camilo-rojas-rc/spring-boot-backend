package com.bezkoder.spring.jwt.mongodb.model;

import org.bson.types.Binary;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "perfiles")
public class Perfil {
    
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private boolean activo = false;

    @NotBlank
    @Size(max = 50)
    private String users;

    private Binary resource;

    public Perfil(boolean activo, String users) {
        super();
        this.activo = activo;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getUser() {
        return users;
    }
    
    public void setUser(String users) {
        this.users = users;
    }

    public Binary getRecurso() {
        return resource;
    }

    public void setRecurso(Binary resource) {
        this.resource = resource;
    }
    
    @Override
    public String toString() {
        return "Perfil [id=" + id + ", activo=" + activo + ", users=" + users + ", resource=" + resource + "]";
    }
}