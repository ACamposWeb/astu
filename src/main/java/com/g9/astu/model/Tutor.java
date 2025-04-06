//Estudiante.java
//Modelo de Estudiante
package com.g9.astu.model;

import jakarta.persistence.*;

@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String especialidad;

    public Tutor() {
    }

    public Tutor(String name, String especialidad) {
        this.name = name;
        this.especialidad = especialidad;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
}
