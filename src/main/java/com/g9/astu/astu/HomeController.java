package com.g9.astu.astu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class HomeController {
    public static void main(String[] args) {
		SpringApplication.run(HomeController.class, args);
	}
    @GetMapping
    public String home() {
        return "¡Bienvenido a ASTU!";
    }
}
