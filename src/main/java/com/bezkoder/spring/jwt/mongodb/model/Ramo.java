package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ramos")
public class Ramo {
    
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String codigo;

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String semestre;

    @NotBlank
    @Size(max = 200)
    private String descripcion;

    public Ramo(String codigo, String nombre, String semestre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.semestre = semestre;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Ramo [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", semestre=" + semestre + ", descripcion=" + descripcion + "]";
    }
}