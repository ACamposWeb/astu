package com.g9.astu.controller;

import com.g9.astu.model.Sesion;
import com.g9.astu.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SesionController {
    @Autowired
    private SesionService service;

    @GetMapping("/sesiones")
    public String listarSesiones(Model model) {
        List<Sesion> sesiones = service.getAll();
        model.addAttribute("sesiones", sesiones);
        return "sesiones";
    }
}
