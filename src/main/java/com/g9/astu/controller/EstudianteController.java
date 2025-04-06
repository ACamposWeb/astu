package com.g9.astu.controller;

import com.g9.astu.model.Estudiante;
import com.g9.astu.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService service;

    @GetMapping
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = service.getAll();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("estudiante", new Estudiante()); // Para el formulario
        return "estudiantes";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        service.save(estudiante);
        return "redirect:/estudiantes";
    }

    // Nuevo: Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Estudiante estudiante = service.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de estudiante inválido: " + id));
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("estudiantes", service.getAll());
        return "estudiantes"; // Misma vista pero con el objeto cargado
    }

    // Nuevo: Eliminar estudiante
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/estudiantes";
    }
}
