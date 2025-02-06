package com.server.gateway.config;

import com.server.gateway.filters.JwtAuthorizationFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate msAuthRestTemplate(RestTemplateBuilder builder, DiscoveryClient discoveryClient) {
        ServiceInstance serviceInstance = discoveryClient.getInstances("AUTH").stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Service AUTH not found"));

        String baseUrl = serviceInstance.getUri().toString();

        return builder.rootUri(baseUrl).build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(RestTemplate msAuthRestTemplate) {
        return new JwtAuthorizationFilter(msAuthRestTemplate);
    }
}
