package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quizpres")
public class QuizPre {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String quizid;

  @NotBlank
  @Size(max = 50)
  private String preguntaid;

  public QuizPre() {
    
  }

  public QuizPre(String quizid, String preguntaid) {
    this.quizid = quizid;
    this.preguntaid = preguntaid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }

  public String getPreguntaid() {
    return preguntaid;
  }

  public void setPreguntaid(String preguntaid) {
    this.preguntaid = preguntaid;
  } 
  
  @Override
    public String toString() {
        return "QuizPre [id=" + id + ", quizid=" + quizid + ", preguntaid=" + preguntaid + "]";
    }
}