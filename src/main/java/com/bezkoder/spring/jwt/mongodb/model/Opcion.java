package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "opciones")
public class Opcion {
  @Id
  private String id;

  @NotBlank
  @Size(max = 200)
  private String opcion;

  @NotBlank
  @Size(max = 50)
  private boolean coincide = false;

  @NotBlank
  @Size(max = 50)
  private String porcentaje;

  @NotBlank
  @Size(max = 50)
  private String preguntas;

  public Opcion() {

  }

  public Opcion(String opcion, boolean coincide, String porcentaje, String preguntas) {
    this.opcion = opcion;
    this.coincide = coincide;
    this.porcentaje = porcentaje;
    this.preguntas = preguntas;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOpcion() {
    return opcion;
  }

  public void setOpcion(String opcion) {
    this.opcion = opcion;
  }

  public boolean getCoincide() {
    return coincide;
  }

  public void setCoincide(boolean coincide) {
    this.coincide = coincide;
  }

  public String getPorcentaje() {
    return porcentaje;
  }

  public void setPorcentaje(String porcentaje) {
    this.porcentaje = porcentaje;
  }

  public String getPregunta() {
    return preguntas;
  }

  public void setPregunta(String preguntas) {
    this.preguntas = preguntas;
  }

  @Override
    public String toString() {
        return "Opcion [id=" + id + ", opcion=" + opcion + ", coincide=" + coincide + 
        ", porcentaje=" + porcentaje + ", preguntas=" + preguntas + "]";
    }
}