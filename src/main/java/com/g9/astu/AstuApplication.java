package com.g9.astu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller  // Cambiado de @RestController a @Controller para renderizar vistas
public class AstuApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AstuApplication.class, args);
    }
    
    @GetMapping("/")
    public String home() {
        return "index";  // Esto buscará src/main/resources/templates/index.html
    }
}