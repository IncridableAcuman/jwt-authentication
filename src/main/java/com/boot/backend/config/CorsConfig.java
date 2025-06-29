package com.boot.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET","PUT","POST","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("Content-Type","Authorization","Origin","Accept"));
        config.setExposedHeaders(List.of("Authorization","Set-Cookie"));
        source.registerCorsConfiguration("/**",config);

        return new CorsFilter(source);
    }
}
