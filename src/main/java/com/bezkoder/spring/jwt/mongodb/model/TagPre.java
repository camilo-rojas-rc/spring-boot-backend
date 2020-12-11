package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tagpres")
public class TagPre {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String tagid;

  @NotBlank
  @Size(max = 50)
  private String preguntaid;

  public TagPre() {

  }

  public TagPre(String tagid, String preguntaid) {
    this.tagid = tagid;
    this.preguntaid = preguntaid;
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

  public String getPreguntaid() {
    return preguntaid;
  }

  public void setPreguntaid(String preguntaid) {
    this.preguntaid = preguntaid;
  }  

  @Override
    public String toString() {
        return "TagPre [id=" + id + ", tagid=" + tagid + ", preguntaid=" + preguntaid + "]";
    }
}