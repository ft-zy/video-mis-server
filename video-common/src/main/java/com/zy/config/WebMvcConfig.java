package com.zy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders(
                        "Content-Type", "X-Requested-With",
                        "accept,Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "token"
                )
                .maxAge(3600)
                .allowCredentials(true);
    }


}
