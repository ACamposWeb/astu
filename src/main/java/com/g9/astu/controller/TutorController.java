package com.g9.astu.controller;

import com.g9.astu.model.Tutor;
import com.g9.astu.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TutorController {
    @Autowired
    private TutorService service;

    @GetMapping("/tutores")
    public String listarTutores(Model model) {
        List<Tutor> tutores = service.getAll();
        model.addAttribute("tutores", tutores);
        return "tutores";
    }
}
