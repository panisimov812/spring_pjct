package com.panem.panem_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * allowedOrigins("http://localhost:8081") указывает,
 * что все запросыс домена http://localhost:8081 разрешены.
 * Изменить на другой домен или порт при необходимости
 *
 *  @author Anisimov Petr (tg: @petr_anisimov)
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}