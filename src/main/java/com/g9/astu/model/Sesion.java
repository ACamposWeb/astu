package com.g9.astu.model;
import jakarta.persistence.*;
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
}
