package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cursos")
public class Curso {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String codigo;

  @NotBlank
  @Size(max = 50)
  private String semestre;

  @NotBlank
  @Size(max = 50)
  private String año;

  @NotBlank
  private String descripcion;

  @NotBlank
  @Size(max = 50)
  private String password;

  @NotBlank
  @Size(max = 50)
  private boolean activo = false;

  @NotBlank
  @Size(max = 50)
  private String ramoid;

  public Curso() {

  }

  public Curso(String codigo, String semestre, String año, String descripcion, String password, boolean activo, String ramoid) {
    this.codigo = codigo;
    this.semestre = semestre;
    this.año = año;
    this.descripcion = descripcion;
    this.password = password;
    this.activo = activo;
    this.ramoid = ramoid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getSemestre() {
    return semestre;
  }

  public void setSemestre(String semestre) {
    this.semestre = semestre;
  }

  public String getAño() {
    return año;
  }

  public void setAño(String año) {
    this.año = año;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean getActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public String getRamoid() {
    return ramoid;
  }

  public void setRamoid(String ramoid) {
    this.ramoid = ramoid;
  }

  @Override
    public String toString() {
        return "Curso [id=" + id + ", codigo=" + codigo + ", semestre=" + semestre + 
        ", año=" + año + ", descripcion=" + descripcion + 
        ", password=" + password + ", activo=" + activo + ", ramoid=" + ramoid + "]";
    }
}