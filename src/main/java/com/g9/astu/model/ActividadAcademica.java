package com.g9.astu.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ActividadAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la sesión donde se realizó la actividad
    @ManyToOne
    @JoinColumn(name = "sesion_id")
    private Sesion sesion;

    // Breve descripción de lo trabajado
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Fecha y hora en que se registró la actividad
    private LocalDateTime fechaRegistro;

    // Ruta o nombre del archivo adjunto (documento, imagen, etc.)
    private String archivoUrl;

    // Constructor vacío requerido por JPA
    public ActividadAcademica() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Constructor con parámetros
    public ActividadAcademica(Sesion sesion, String descripcion, String archivoUrl) {
        this.sesion = sesion;
        this.descripcion = descripcion;
        this.archivoUrl = archivoUrl;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getArchivoUrl() {
        return archivoUrl;
    }

    public void setArchivoUrl(String archivoUrl) {
        this.archivoUrl = archivoUrl;
    }
}
