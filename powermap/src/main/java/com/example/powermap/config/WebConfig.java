package com.example.powermap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS para todos os endpoints
                .allowedOrigins(
                        "http://localhost:4200", // Permite localhost para desenvolvimento
                        "https://powermap-production-a419.up.railway.app" // Permite o domínio de produção
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true); // Permite envio de cookies ou autenticação
    }
}
