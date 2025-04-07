package com.g9.astu.service;

import com.g9.astu.model.Sesion;
import com.g9.astu.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionService {
    @Autowired
    private SesionRepository repository;

    public List<Sesion> getAll() {
        return repository.findAll();
    }
    public Optional<Sesion> getById(Long id) {
        return repository.findById(id);
    }
    
    public Sesion save(Sesion sesion) {
        return repository.save(sesion);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
