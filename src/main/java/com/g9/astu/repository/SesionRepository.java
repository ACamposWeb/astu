package com.g9.astu.repository;

import com.g9.astu.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByEstudianteId(Long estudianteId);
}
