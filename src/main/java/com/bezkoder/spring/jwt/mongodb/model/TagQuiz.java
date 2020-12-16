package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tagquizs")
public class TagQuiz {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String tagid;

  @NotBlank
  @Size(max = 50)
  private String quizid;

  public TagQuiz() {

  }

  public TagQuiz(String tagid, String quizid) {
    this.tagid = tagid;
    this.quizid = quizid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTagid() {
    return tagid;
  }

  public void setTagid(String tagid) {
    this.tagid = tagid;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }  

  @Override
    public String toString() {
        return "TagQuiz [id=" + id + ", tagid=" + tagid + ", quizid=" + quizid + "]";
    }
}