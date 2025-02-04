package com.ms.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Bean
    public RestTemplate msSchoolRestTemplate(RestTemplateBuilder builder, DiscoveryClient discoveryClient) {
        ServiceInstance serviceInstance = discoveryClient.getInstances("SCHOOL").getFirst();
        String baseUrl = serviceInstance.getUri().toString();
        return builder
                .rootUri(baseUrl)
                .build();
    }
}
