package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quizcurs")
public class QuizCur {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String quizid;

  @NotBlank
  @Size(max = 50)
  private String cursoid;

  public QuizCur() {
    
  }

  public QuizCur(String quizid, String cursoid) {
    this.quizid = quizid;
    this.cursoid = cursoid;
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

  public String getCursoid() {
    return cursoid;
  }

  public void setCursoid(String cursoid) {
    this.cursoid = cursoid;
  } 
  
  @Override
    public String toString() {
        return "QuizCur [id=" + id + ", quizid=" + quizid + ", cursoid=" + cursoid + "]";
    }
}