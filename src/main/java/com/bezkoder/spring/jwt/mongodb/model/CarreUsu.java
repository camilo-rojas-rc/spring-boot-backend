package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carreusus")
public class CarreUsu {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String carreraid;

  @NotBlank
  @Size(max = 50)
  private String usuarioid;

  public CarreUsu() {

  }

  public CarreUsu(String carreraid, String usuarioid) {
    this.carreraid = carreraid;
    this.usuarioid = usuarioid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCarreraid() {
    return carreraid;
  }

  public void setCarreraid(String carreraid) {
    this.carreraid = carreraid;
  }

  public String getUsuarioid() {
    return usuarioid;
  }

  public void setUsuarioid(String usuarioid) {
    this.usuarioid = usuarioid;
  }  

  @Override
    public String toString() {
        return "CarreUsu [id=" + id + ", carreraid=" + carreraid + ", usuarioid=" + usuarioid + "]";
    }
}