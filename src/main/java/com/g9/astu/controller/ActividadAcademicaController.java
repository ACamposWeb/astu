package com.g9.astu.controller;

import com.g9.astu.model.ActividadAcademica;
import com.g9.astu.model.Sesion;
import com.g9.astu.service.ActividadAcademicaService;
import com.g9.astu.service.SesionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/actividades")
public class ActividadAcademicaController {

    private final ActividadAcademicaService actividadService;
    private final SesionService sesionService;

    public ActividadAcademicaController(ActividadAcademicaService actividadService, SesionService sesionService) {
        this.actividadService = actividadService;
        this.sesionService = sesionService;
    }

    @GetMapping
    public String listarActividades(Model model) {
        model.addAttribute("actividad", new ActividadAcademica()); // por si usas binding
        model.addAttribute("actividades", actividadService.findAll());
        model.addAttribute("sesiones", sesionService.getAll()); // necesario para el combo de sesiones
        return "lista_actividades";
    }

    // Procesar el formulario
    @PostMapping("/guardar")
    public String registrarActividad(
            @RequestParam("sesionId") Long sesionId,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("archivo") MultipartFile archivo
    ) throws IOException {

        Sesion sesion = sesionService.getById(sesionId).orElse(null);
        if (sesion == null) {
            return "redirect:/actividades/error/sesion-no-encontrada";
        }

        String nombreArchivo = null;

        if (!archivo.isEmpty()) {
            // Ruta para guardar archivos (puedes personalizarla o usar un servicio)
            String uploadDir = "/app/uploads";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            archivo.transferTo(new File(uploadDir + File.separator + nombreArchivo));
        }

        ActividadAcademica actividad = new ActividadAcademica(sesion, descripcion, nombreArchivo);
        actividadService.save(actividad);

        return "redirect:/actividades";
    }
    
    // Mostrar formulario para registrar actividad
    /*@GetMapping("/nueva")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("actividad", new ActividadAcademica());
        model.addAttribute("sesiones", sesionService.getAll());
        return "registro_actividad"; // Thymeleaf o tu motor de plantilla
    }*/
    // Ver todas las actividades
    

    @GetMapping("/error/sesion-no-encontrada")
    public String sesionNoEncontrada() {
        return "error_sesion_no_encontrada"; // Nombre de la vista Thymeleaf
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarActividad(@PathVariable Long id) {
        actividadService.eliminarActividad(id);
        return "redirect:/actividades";
    }

}
