package com.g9.astu.astu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupCheck implements CommandLineRunner {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Override
    public void run(String... args) {
        System.out.println("CLIENT_ID USADO EN RUNTIME: " + clientId);
    }
}
