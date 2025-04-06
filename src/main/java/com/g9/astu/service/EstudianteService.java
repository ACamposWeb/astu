package com.g9.astu.service;

import com.g9.astu.model.Estudiante;
import com.g9.astu.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository repository;

    public List<Estudiante> getAll() {
        return repository.findAll();
    }
}
