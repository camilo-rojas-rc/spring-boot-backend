package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "respuestas")
public class Respuesta {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String tiemporespuesta;

  @NotBlank
  @Size(max = 50)
  private String respuesta1 = null;

  @NotBlank
  @Size(max = 50)
  private String respuesta2 = null;

  @NotBlank
  @Size(max = 50)
  private String respuesta3 = null;

  @NotBlank
  @Size(max = 50)
  private String respuesta4 = null;

  @NotBlank
  @Size(max = 50)
  private String respuesta5 = null;

  @NotBlank
  @Size(max = 50)
  private String puntaje;

  @NotBlank
  private String usuarioid;

  @NotBlank
  @Size(max = 50)
  private String preguntaid;

  @NotBlank
  @Size(max = 50)
  private String quizid;

  public Respuesta() {

  }

  public Respuesta(String tiemporespuesta, String respuesta1, String respuesta2, String respuesta3, String respuesta4, String respuesta5, String puntaje, String usuarioid, String preguntaid, String quizid) {
    this.tiemporespuesta = tiemporespuesta;
    this.respuesta1 = respuesta1;
    this.respuesta2 = respuesta2;
    this.respuesta3 = respuesta3;
    this.respuesta4 = respuesta4;
    this.respuesta5 = respuesta5;
    this.puntaje = puntaje;
    this.usuarioid = usuarioid;
    this.preguntaid = preguntaid;
    this.quizid = quizid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTiemporespuesta() {
    return tiemporespuesta;
  }

  public void setTiemporespuesta(String tiemporespuesta) {
    this.tiemporespuesta = tiemporespuesta;
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

  public String getPuntaje() {
    return puntaje;
  }

  public void setPuntaje(String puntaje) {
    this.puntaje = puntaje;
  }

  public String getUsuarioid() {
    return usuarioid;
  }

  public void setUsuarioid(String usuarioid) {
    this.usuarioid = usuarioid;
  }

  public String getPreguntaid() {
    return preguntaid;
  }

  public void setPreguntaid(String preguntaid) {
    this.preguntaid = preguntaid;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }

  @Override
    public String toString() {
        return "Respuesta [id=" + id + ", tiemporespuesta=" + tiemporespuesta + ", respuesta1=" + respuesta1 + 
        ", respuesta2=" + respuesta2 + ", respuesta3=" + respuesta3 + ", respuesta4=" + respuesta4 + 
        ", respuesta5=" + respuesta5 + ", puntaje=" + puntaje + ", usuarioid=" + usuarioid + ", preguntaid=" + preguntaid + 
        ", quizid=" + quizid + "]";
    }
}