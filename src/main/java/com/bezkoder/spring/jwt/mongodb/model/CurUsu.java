package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "curusus")
public class CurUsu {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String cursoid;

  @NotBlank
  @Size(max = 50)
  private String usuarioid;

  public CurUsu() {

  }

  public CurUsu(String cursoid, String usuarioid) {
    this.cursoid = cursoid;
    this.usuarioid = usuarioid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCursoid() {
    return cursoid;
  }

  public void setCursoid(String cursoid) {
    this.cursoid = cursoid;
  }

  public String getUsuarioid() {
    return usuarioid;
  }

  public void setUsuarioid(String usuarioid) {
    this.usuarioid = usuarioid;
  }  

  @Override
    public String toString() {
        return "CurUsu [id=" + id + ", cursoid=" + cursoid + ", usuarioid=" + usuarioid + "]";
    }
}