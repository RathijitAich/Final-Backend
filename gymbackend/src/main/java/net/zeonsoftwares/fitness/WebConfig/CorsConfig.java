package net.zeonsoftwares.fitness.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("http://localhost:3000"); // Replace with your frontend origin
        config.addAllowedMethod("GET");      // Allow GET requests
        config.addAllowedMethod("POST");     // Allow POST requests
        config.addAllowedMethod("PUT");      // Allow PUT requests
        config.addAllowedMethod("DELETE");   // Allow DELETE requests
        config.addAllowedMethod("OPTIONS");  // Allow pre-flight requests
        config.addAllowedHeader("*");        // Allow all headers
        config.setAllowCredentials(true);    // Allow credentials (cookies, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints
        return new CorsFilter(source);
    }
}
