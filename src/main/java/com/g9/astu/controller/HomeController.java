package com.g9.astu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("usuario", authentication != null ? authentication.getName() : "Invitado");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // mostrará login
    }
}