//Estudiante.java
//Modelo de Estudiante
package com.g9.astu.model;
import jakarta.persistence.*;

@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String program;
    
    public Estudiante() {
    }

    
    public Estudiante(String name, String email, String program) {
        this.name = name;
        this.email = email;
        this.program = program;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }

    
}
