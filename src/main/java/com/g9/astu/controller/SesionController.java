package com.g9.astu.controller;

import com.g9.astu.model.*;
import com.g9.astu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.LocalTime;
//import java.util.List;
import java.util.List;

@Controller
@RequestMapping("/sesiones")
public class SesionController {
    
    @Autowired
    private SesionService sesionService;
    
    @Autowired
    private EstudianteService estudianteService;
    
    @Autowired
    private TutorService tutorService;

    @GetMapping
    public String listarSesiones(Model model) {
        model.addAttribute("sesiones", sesionService.getAll());
        model.addAttribute("sesion", new Sesion());
        model.addAttribute("estudiantes", estudianteService.getAll());
        model.addAttribute("tutores", tutorService.getAll());
        model.addAttribute("fechaActual", LocalDate.now());
        model.addAttribute("horaActual", LocalTime.now().withMinute(0).withSecond(0));
        return "sesiones";
    }
    
    @PostMapping("/guardar")
    public String guardarSesion(@ModelAttribute Sesion sesion,
                              @RequestParam("fecha") String fechaStr,
                              @RequestParam("hora") String horaStr,
                              @RequestParam("estudianteId") Long estudianteId,
                              @RequestParam("tutorId") Long tutorId,
                              RedirectAttributes redirectAttributes) {
        
        sesion.setFecha(LocalDate.parse(fechaStr));
        sesion.setHora(LocalTime.parse(horaStr));
        sesion.setEstudiante(estudianteService.getById(estudianteId).orElse(null));
        sesion.setTutor(tutorService.getById(tutorId).orElse(null));
        
        sesionService.save(sesion);
        
        String mensaje = sesion.getId() == null ? 
                        "Sesión creada exitosamente" : 
                        "Sesión actualizada exitosamente";
        
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/sesiones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Sesion sesion = sesionService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de sesión inválido: " + id));
        
        model.addAttribute("sesion", sesion);
        model.addAttribute("estudiantes", estudianteService.getAll());
        model.addAttribute("tutores", tutorService.getAll());
        model.addAttribute("sesiones", sesionService.getAll());
        return "sesiones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarSesion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        sesionService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Sesión eliminada exitosamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/sesiones";
    }

    @GetMapping("/historial")
public String historialSesiones(
        @RequestParam(required = false) Long estudianteId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
        @RequestParam(required = false) Long tutorId,
        Model model) {

    model.addAttribute("estudiantes", estudianteService.getAll());
    model.addAttribute("tutores", tutorService.getAll());

    List<Sesion> sesiones = sesionService.buscarHistorial(estudianteId, fechaInicio, fechaFin, tutorId);
    model.addAttribute("sesiones", sesiones);

    return "historial-sesiones";
}

}