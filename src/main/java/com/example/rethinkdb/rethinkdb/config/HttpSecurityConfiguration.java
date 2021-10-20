package com.example.rethinkdb.rethinkdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Order(1)
public class HttpSecurityConfiguration {

    private final ApplicationProperties applicationProperties;

    public HttpSecurityConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        if (!CollectionUtils.isEmpty(applicationProperties.cors.getAllowedOrigins())) {

            source.registerCorsConfiguration("/**", applicationProperties.cors);

        }

        return source;
    }
}
