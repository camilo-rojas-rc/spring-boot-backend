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
  @Size(max = 200)
  private String opcion1 = null;

  @NotBlank
  @Size(max = 200)
  private String opcion2 = null;

  @NotBlank
  @Size(max = 200)
  private String opcion3 = null;

  @NotBlank
  @Size(max = 200)
  private String opcion4 = null;

  @NotBlank
  @Size(max = 200)
  private String opcion5 = null;

  @NotBlank
  @Size(max = 20)
  private String respuesta1 = null;

  @NotBlank
  @Size(max = 20)
  private String respuesta2 = null;

  @NotBlank
  @Size(max = 20)
  private String respuesta3 = null;

  @NotBlank
  @Size(max = 20)
  private String respuesta4 = null;

  @NotBlank
  @Size(max = 20)
  private String respuesta5 = null;

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

  public Pregunta(String titulo, String tipo, String enunciado, String opcion1, String opcion2, String opcion3, String opcion4, String opcion5, String respuesta1, String respuesta2, String respuesta3, String respuesta4, String respuesta5, String tiemporespuesta, String puntaje, boolean random, String users) {
    this.titulo = titulo;
    this.tipo = tipo;
    this.enunciado = enunciado;
    this.opcion1 = opcion1;
    this.opcion2 = opcion2;
    this.opcion3 = opcion3;
    this.opcion4 = opcion4;
    this.opcion5 = opcion5;
    this.respuesta1 = respuesta1;
    this.respuesta2 = respuesta2;
    this.respuesta3 = respuesta3;
    this.respuesta4 = respuesta4;
    this.respuesta5 = respuesta5;
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

  public String getOpcion1() {
    return opcion1;
  }

  public void setOpcion1(String opcion1) {
    this.opcion1 = opcion1;
  }

  public String getOpcion2() {
    return opcion2;
  }

  public void setOpcion2(String opcion2) {
    this.opcion2 = opcion2;
  }

  public String getOpcion3() {
    return opcion3;
  }

  public void setOpcion3(String opcion3) {
    this.opcion3 = opcion3;
  }

  public String getOpcion4() {
    return opcion4;
  }

  public void setOpcion4(String opcion4) {
    this.opcion4 = opcion4;
  }

  public String getOpcion5() {
    return opcion5;
  }

  public void setOpcion5(String opcion5) {
    this.opcion5 = opcion5;
  }

  public String getRespuesta1() {
    return respuesta1;
  }

  public void setRespuesta1(String respuesta1) {
    this.respuesta1 = respuesta1;
  }

  public String getRespuesta2() {
    return respuesta2;
  }

  public void setRespuesta2(String respuesta2) {
    this.respuesta2 = respuesta2;
  }

  public String getRespuesta3() {
    return respuesta3;
  }

  public void setRespuesta3(String respuesta3) {
    this.respuesta3 = respuesta3;
  }

  public String getRespuesta4() {
    return respuesta4;
  }

  public void setRespuesta4(String respuesta4) {
    this.respuesta4 = respuesta4;
  }

  public String getRespuesta5() {
    return respuesta5;
  }

  public void setRespuesta5(String respuesta5) {
    this.respuesta5 = respuesta5;
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
        return "Pregunta [id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", enunciado=" + enunciado + 
        ", opcion1=" + opcion1 + ", opcion2=" + opcion2 + ", opcion3=" + opcion3 + ", opcion4=" + opcion4 + 
        ", opcion5=" + opcion5 + ", respuesta1=" + respuesta1 + ", respuesta2=" + respuesta2 + 
        ", respuesta3=" + respuesta3 + ", respuesta4=" + respuesta4 + ", respuesta5=" + respuesta5 + 
        ", tiemporespuesta=" + tiemporespuesta + ", puntaje=" + puntaje + ", random=" + random + ", users=" + users + "]";
    }
}
