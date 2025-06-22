package com.g9.astu.service;

import com.g9.astu.model.Sesion;
import com.g9.astu.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;


@Service
public class SesionService {

    @Autowired
    private SesionRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Sesion> buscarHistorial(Long estudianteId, LocalDate fechaInicio, LocalDate fechaFin, Long tutorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sesion> cq = cb.createQuery(Sesion.class);
        Root<Sesion> sesion = cq.from(Sesion.class);

        List<Predicate> predicates = new ArrayList<>();

        if (estudianteId != null) {
            predicates.add(cb.equal(sesion.get("estudiante").get("id"), estudianteId));
        }
        if (fechaInicio != null) {
            predicates.add(cb.greaterThanOrEqualTo(sesion.get("fecha"), fechaInicio));
        }
        if (fechaFin != null) {
            predicates.add(cb.lessThanOrEqualTo(sesion.get("fecha"), fechaFin));
        }
        if (tutorId != null) {
            predicates.add(cb.equal(sesion.get("tutor").get("id"), tutorId));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
