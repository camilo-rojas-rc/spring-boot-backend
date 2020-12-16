package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carreras")
public class Carrera {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String malla;

  public Carrera() {

  }

  public Carrera(String malla) {
    this.malla = malla;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMalla() {
    return malla;
  }

  public void setMalla(String malla) {
    this.malla = malla;
  }

  @Override
    public String toString() {
        return "Carrera [id=" + id + ", malla=" + malla + "]";
    }
}