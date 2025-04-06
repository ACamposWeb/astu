package com.g9.astu.controller;

import com.g9.astu.model.Estudiante;
import com.g9.astu.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class EstudianteController {
    @Autowired
    private EstudianteService service;

    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = service.getAll();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes";
    }
}
