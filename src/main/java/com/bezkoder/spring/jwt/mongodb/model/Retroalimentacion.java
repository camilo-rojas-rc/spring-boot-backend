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
  @Size(max = 200)
  private String enunciado;

  @NotBlank
  @Size(max = 50)
  private boolean activo = false;

  @NotBlank
  @Size(max = 50)
  private String preguntaid;

  public Retroalimentacion() {

  }

  public Retroalimentacion(String enunciado, boolean activo, String preguntaid) {
    this.enunciado = enunciado;
    this.activo = activo;
    this.preguntaid = preguntaid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEnunciado() {
    return enunciado;
  }

  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }  

  public boolean getActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }  

  public String getPreguntaid() {
    return preguntaid;
  }

  public void setPreguntaid(String preguntaid) {
    this.preguntaid = preguntaid;
  }  

  @Override
    public String toString() {
        return "Retroalimentacion [id=" + id + ", enunciado=" + enunciado + ", activo=" + activo + ", preguntaid=" + preguntaid + "]";
    }
}