package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
public class Tag {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String nombre;

  public Tag() {

  }

  public Tag(String nombre) {
    this.nombre = nombre;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
    public String toString() {
        return "Tag [id=" + id + ", nombre=" + nombre + "]";
    }
}