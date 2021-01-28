package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuquizs")
public class UsuQuiz {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String usuarioid;

  @NotBlank
  @Size(max = 50)
  private String quizid;

  @NotBlank
  @Size(max = 50)
  private String puntajetotal;

  public UsuQuiz() {

  }

  public UsuQuiz(String usuarioid, String quizid, String puntajetotal) {
    this.usuarioid = usuarioid;
    this.quizid = quizid;
    this.puntajetotal = puntajetotal;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsuarioid() {
    return usuarioid;
  }

  public void setUsuarioid(String usuarioid) {
    this.usuarioid = usuarioid;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }

  public String getPuntajetotal() {
    return puntajetotal;
  }

  public void setPuntajetotal(String puntajetotal) {
    this.puntajetotal = puntajetotal;
  }

  @Override
    public String toString() {
        return "UsuQuiz [id=" + id + ", usuarioid=" + usuarioid + ", quizid=" + quizid + ", puntajetotal=" + puntajetotal + "]";
    }
}