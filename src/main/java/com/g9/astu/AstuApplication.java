package com.g9.astu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@SpringBootApplication

public class AstuApplication {
    
    public static void main(String[] args) {
		SpringApplication.run(AstuApplication.class, args);
	}
    @GetMapping
    public String home() {
        return "¡Bienvenido a ASTU! Esta es una Prueba";
    }
}
