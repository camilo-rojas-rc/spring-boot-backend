package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "retroalimentacions")
public class Retroalimentacion {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String tipo;

  @NotBlank
  @Size(max = 50)
  private String preguntaid;

  public Retroalimentacion() {

  }

  public Retroalimentacion(String tipo, String preguntaid) {
    this.tipo = tipo;
    this.preguntaid = preguntaid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getPreguntaid() {
    return preguntaid;
  }

  public void setPreguntaid(String preguntaid) {
    this.preguntaid = preguntaid;
  }  

  @Override
    public String toString() {
        return "Retroalimentacion [id=" + id + ", tipo=" + tipo + ", preguntaid=" + preguntaid + "]";
    }
}