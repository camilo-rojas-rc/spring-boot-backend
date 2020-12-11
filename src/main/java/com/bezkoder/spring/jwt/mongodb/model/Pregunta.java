package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "preguntas")
public class Pregunta {
  @Id
  private String id;

  @NotBlank
  @Size(max = 100)
  private String titulo;

  @NotBlank
  @Size(max = 50)
  private String tipo;

  @NotBlank
  @Size(max = 200)
  private String enunciado;

  @NotBlank
  @Size(max = 50)
  private String tiemporespuesta;

  @NotBlank
  @Size(max = 50)
  private String puntaje;

  @NotBlank
  @Size(max = 50)
  private boolean random = false;

  @NotBlank
  @Size(max = 50)
  private String users;

  public Pregunta() {

  }

  public Pregunta(String titulo, String tipo, String enunciado, String tiemporespuesta, String puntaje, boolean random, String users) {
    this.titulo = titulo;
    this.tipo = tipo;
    this.enunciado = enunciado;
    this.tiemporespuesta = tiemporespuesta;
    this.puntaje = puntaje;
    this.random = random;
    this.users = users;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getEnunciado() {
    return enunciado;
  }

  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }

  public String getTiempoRespuesta() {
    return tiemporespuesta;
  }

  public void setTiempoRespuesta(String tiemporespuesta) {
    this.tiemporespuesta = tiemporespuesta;
  }

  public String getPuntaje() {
    return puntaje;
  }

  public void setPuntaje(String puntaje) {
    this.puntaje = puntaje;
  }

  public boolean getRandom() {
    return random;
  }

  public void setRandom(boolean random) {
    this.random = random;
  }

  public String getUser() {
    return users;
  }

  public void setUser(String users) {
    this.users = users;
  }

  @Override
    public String toString() {
        return "Pregunta [id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + 
        ", enunciado=" + enunciado + ", tiemporespuesta=" + tiemporespuesta + 
        ", puntaje=" + puntaje + ", random=" + random + ", users=" + users + "]";
    }
}
