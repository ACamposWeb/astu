package com.g9.astu.service;

import com.g9.astu.model.Tutor;
import com.g9.astu.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    public List<Tutor> getAll() {
        return repository.findAll();
    }
    public Optional<Tutor> getById(Long id) {
        return repository.findById(id);
    }
    
    public Tutor save(Tutor tutor) {
        return repository.save(tutor);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
