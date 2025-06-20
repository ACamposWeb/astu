package com.g9.astu.service;

import com.g9.astu.model.Estudiante;
import com.g9.astu.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository repository;

    public List<Estudiante> getAll() {
        return repository.findAll();
    }
    public Estudiante save(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Optional<Estudiante> getById(Long id) {
        return repository.findById(id);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
