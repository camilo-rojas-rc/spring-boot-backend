package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carreramos")
public class CarreRamo {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String carreraid;

  @NotBlank
  @Size(max = 50)
  private String ramoid;

  public CarreRamo() {

  }

  public CarreRamo(String carreraid, String ramoid) {
    this.carreraid = carreraid;
    this.ramoid = ramoid;
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

  public String getRamoid() {
    return ramoid;
  }

  public void setRamoid(String ramoid) {
    this.ramoid = ramoid;
  }  

  @Override
    public String toString() {
        return "CarreRamo [id=" + id + ", carreraid=" + carreraid + ", ramoid=" + ramoid + "]";
    }
}