package com.g9.astu.repository;

import com.g9.astu.model.ActividadAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadAcademicaRepository extends JpaRepository<ActividadAcademica, Long> {
}
