package com.g9.astu.service;

import com.g9.astu.model.Sesion;
import com.g9.astu.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionService {
    @Autowired
    private SesionRepository repository;

    public List<Sesion> getAll() {
        return repository.findAll();
    }
}
