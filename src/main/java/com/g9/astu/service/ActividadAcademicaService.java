package com.g9.astu.service;
import com.g9.astu.model.ActividadAcademica;
import com.g9.astu.repository.ActividadAcademicaRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadAcademicaService {

    private final ActividadAcademicaRepository actividadRepository;

    public ActividadAcademicaService(ActividadAcademicaRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public void save(ActividadAcademica actividad) {
        actividadRepository.save(actividad);
    }

    public List<ActividadAcademica> findAll() {
        return actividadRepository.findAll();
    }

    public ActividadAcademica findById(Long id) {
        Optional<ActividadAcademica> optional = actividadRepository.findById(id);
        return optional.orElse(null);
    }
    public void eliminarActividad(Long id) {
        ActividadAcademica actividad = actividadRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        // Eliminar archivo físico si existe
        if (actividad.getArchivoUrl() != null) {
            File archivo = new File("/app/uploads/" + actividad.getArchivoUrl());
            if (archivo.exists()) {
                archivo.delete();
            }
        }

        // Eliminar registro en base de datos
        actividadRepository.deleteById(id);
    }

}
