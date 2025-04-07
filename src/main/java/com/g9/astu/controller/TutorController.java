package com.g9.astu.controller;

import com.g9.astu.model.Tutor;
import com.g9.astu.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tutores")
public class TutorController {
    @Autowired
    private TutorService service;

    @GetMapping
    public String listarTutores(Model model) {
        List<Tutor> tutores = service.getAll();
        model.addAttribute("tutores", tutores);
        model.addAttribute("tutor", new Tutor()); // Para el formulario
        return "tutores";
    }
    @PostMapping("/guardar")
    public String guardarTutor(@ModelAttribute Tutor tutor, RedirectAttributes redirectAttributes) {
        service.save(tutor);
        String mensaje = tutor.getId() == null ? "Tutor creado exitosamente" : "Tutor actualizado exitosamente";
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tutores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Tutor tutor = service.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de tutor inválido: " + id));
        model.addAttribute("tutor", tutor);
        model.addAttribute("tutores", service.getAll());
        return "tutores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTutor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Tutor eliminado exitosamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tutores";
    }
}
