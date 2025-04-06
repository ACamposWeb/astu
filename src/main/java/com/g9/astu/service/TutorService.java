package com.g9.astu.service;

import com.g9.astu.model.Tutor;
import com.g9.astu.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    public List<Tutor> getAll() {
        return repository.findAll();
    }
}
