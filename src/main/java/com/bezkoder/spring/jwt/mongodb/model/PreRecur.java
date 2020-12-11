package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prerecurs")
public class PreRecur {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String recursoid;

  @NotBlank
  @Size(max = 50)
  private String preguntaid;

  public PreRecur() {

  }

  public PreRecur(String preguntaid, String recursoid) {
    this.preguntaid = preguntaid;
    this.recursoid = recursoid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRecursoid() {
    return recursoid;
  }

  public void setRecursoid(String recursoid) {
    this.recursoid = recursoid;
  }

  public String getPreguntaid() {
    return preguntaid;
  }

  public void setPreguntaid(String preguntaid) {
    this.preguntaid = preguntaid;
  }  

  @Override
  public String toString() {
      return "PreRecur [id=" + id + ", recursoid=" + recursoid + ", preguntaid=" + preguntaid + "]";
  }
}