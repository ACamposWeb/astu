package com.g9.astu.repository;

import com.g9.astu.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {

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
