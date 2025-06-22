package com.g9.astu.repository;

import com.g9.astu.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByEstudianteId(Long estudianteId);

    @Query("SELECT s FROM Sesion s " +
           "WHERE (:estudianteId IS NULL OR s.estudiante.id = :estudianteId) " +
           "AND (:fechaInicio IS NULL OR s.fecha >= :fechaInicio) " +
           "AND (:fechaFin IS NULL OR s.fecha <= :fechaFin) " +
           "AND (:tutorId IS NULL OR s.tutor.id = :tutorId)")
    List<Sesion> buscarHistorialFiltrado(
        @Param("estudianteId") Long estudianteId,
        @Param("fechaInicio") LocalDate fechaInicio,
        @Param("fechaFin") LocalDate fechaFin,
        @Param("tutorId") Long tutorId
    );
}

