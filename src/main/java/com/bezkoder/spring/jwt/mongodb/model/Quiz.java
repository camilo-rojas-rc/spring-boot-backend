package com.bezkoder.spring.jwt.mongodb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quizs")
public class Quiz {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  private String titulo;

  @NotBlank
  @Size(max = 200)
  private String descripcion;

  @NotBlank
  @Size(max = 50)
  private boolean activo = false;

  @NotBlank
  @Size(max = 50)
  private String tiempodisponible;

  @NotBlank
  @Size(max = 50)
  private String usuarioid;

  @NotBlank
  @Size(max = 50)
  private String fechacreacion;

  @NotBlank
  @Size(max = 50)
  private String fechatermino;

  @NotBlank
  @Size(max = 50)
  private boolean privado = false;

  public Quiz() {
    
  }

  public Quiz(String titulo, String descripcion, boolean activo, String tiempodisponible, String usuarioid, String fechacreacion, String fechatermino, boolean privado) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.activo = activo;
    this.tiempodisponible = tiempodisponible;
    this.usuarioid = usuarioid;
    this.fechacreacion = fechacreacion;
    this.fechatermino = fechatermino;
    this.privado = privado;
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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public boolean getActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  } 

  public String getTiempodisponible() {
    return tiempodisponible;
  }

  public void setTiempodisponible(String tiempodisponible) {
    this.tiempodisponible = tiempodisponible;
  } 

  public String getUsuarioid() {
    return usuarioid;
  }

  public void setUsuarioid(String usuarioid) {
    this.usuarioid = usuarioid;
  }

  public String getFechacreacion() {
    return fechacreacion;
  }

  public void setFechacreacion(String fechacreacion) {
    this.fechacreacion = fechacreacion;
  } 

  public String getFechatermino() {
    return fechatermino;
  }

  public void setFechatermino(String fechatermino) {
    this.fechatermino = fechatermino;
  } 

  public boolean getPrivado() {
    return privado;
  }

  public void setPrivado(boolean privado) {
    this.privado = privado;
  }
  
  @Override
    public String toString() {
        return "Quiz [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + 
        ", activo=" + activo + ", tiempodisponible=" + tiempodisponible + 
        ", usuarioid=" + usuarioid + ", fechacreacion=" + fechacreacion + 
        ", fechatermino=" + fechatermino + ", privado=" + privado + "]";
    }
}