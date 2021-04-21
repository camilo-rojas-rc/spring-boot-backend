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
  private String tipo = "alternativa";

  @NotBlank
  @Size(max = 200)
  private String enunciado;

  @NotBlank
  @Size(max = 200)
  private String subenunciado1;

  @NotBlank
  @Size(max = 200)
  private String subenunciado2;

  @NotBlank
  @Size(max = 200)
  private String subenunciado3;

  @NotBlank
  @Size(max = 200)
  private String subenunciado4;

  @NotBlank
  @Size(max = 50)
  private String template;

  @Size(max = 200)
  private String opcion1 = null;

  @Size(max = 200)
  private String opcion2 = null;

  @Size(max = 200)
  private String opcion3 = null;

  @Size(max = 200)
  private String opcion4 = null;

  @Size(max = 20)
  private String respuesta1 = null;

  @Size(max = 20)
  private String respuesta2 = null;

  @Size(max = 20)
  private String respuesta3 = null;

  @Size(max = 20)
  private String respuesta4 = null;

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
  private boolean privado = false;

  @NotBlank
  @Size(max = 50)
  private String users;

  public Pregunta() {

  }

  public Pregunta(String titulo, String tipo, String enunciado, String subenunciado1, String subenunciado2, String subenunciado3, String subenunciado4, String template, String opcion1, String opcion2, String opcion3, String opcion4, String respuesta1, String respuesta2, String respuesta3, String respuesta4, String tiemporespuesta, String puntaje, boolean random, boolean privado, String users) {
    this.titulo = titulo;
    this.tipo = tipo;
    this.enunciado = enunciado;
    this.subenunciado1 = subenunciado1;
    this.subenunciado2 = subenunciado2;
    this.subenunciado3 = subenunciado3;
    this.subenunciado4 = subenunciado4;
    this.template = template;
    this.opcion1 = opcion1;
    this.opcion2 = opcion2;
    this.opcion3 = opcion3;
    this.opcion4 = opcion4;
    this.respuesta1 = respuesta1;
    this.respuesta2 = respuesta2;
    this.respuesta3 = respuesta3;
    this.respuesta4 = respuesta4;
    this.tiemporespuesta = tiemporespuesta;
    this.puntaje = puntaje;
    this.random = random;
    this.privado = privado;
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

  public String getSubenunciado1() {
    return subenunciado1;
  }

  public void setSubenunciado1(String subenunciado1) {
    this.subenunciado1 = subenunciado1;
  }

  public String getSubenunciado2() {
    return subenunciado2;
  }

  public void setSubenunciado2(String subenunciado2) {
    this.subenunciado2 = subenunciado2;
  }

  public String getSubenunciado3() {
    return subenunciado3;
  }

  public void setSubenunciado3(String subenunciado3) {
    this.subenunciado3 = subenunciado3;
  }

  public String getSubenunciado4() {
    return subenunciado4;
  }

  public void setSubenunciado4(String subenunciado4) {
    this.subenunciado4 = subenunciado4;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
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

  public boolean getPrivado() {
    return privado;
  }

  public void setPrivado(boolean privado) {
    this.privado = privado;
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
        ", subenunciado1=" + subenunciado1 + ", subenunciado2=" + subenunciado2 + ", subenunciado3=" + subenunciado3 + ", subenunciado4=" + subenunciado4 + 
        ", template=" + template + ", opcion1=" + opcion1 + ", opcion2=" + opcion2 + ", opcion3=" + opcion3 + ", opcion4=" + opcion4 + 
        ", respuesta1=" + respuesta1 + ", respuesta2=" + respuesta2 + ", respuesta3=" + respuesta3 + 
        ", respuesta4=" + respuesta4 + ", tiemporespuesta=" + tiemporespuesta + ", puntaje=" + puntaje + 
        ", random=" + random + ", privado=" + privado + ", users=" + users + "]";
    }
}
