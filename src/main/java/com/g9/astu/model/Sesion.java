package com.g9.astu.model;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Tutor tutor;

    public Sesion() {
    }
    public Sesion(Long id, LocalDate fecha, LocalTime hora, Estudiante estudiante, Tutor tutor) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estudiante = estudiante;
        this.tutor = tutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getFechaFormateada() {
        if (fecha == null) return "Sin fecha";
        return fecha.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public String getHoraFormateada() {
        if (hora == null) return "Sin hora";
        return hora.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        String fechaStr = (fecha != null) ? new SimpleDateFormat("dd/MM/yyyy").format(fecha) : "Sin fecha";
        String horaStr = (hora != null) ? hora.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) : "Sin hora";
        String estudianteStr = (estudiante != null) ? estudiante.getName() : "Sin estudiante";
        String tutorStr = (tutor != null) ? tutor.getName() : "Sin tutor";
        return fechaStr + " - " + horaStr + " - " + estudianteStr + " / " + tutorStr;
    }

    
}
