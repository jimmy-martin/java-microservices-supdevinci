package com.server.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthorizationFilter extends AbstractGatewayFilterFactory<JwtAuthorizationFilter.Config> {
    private final RestTemplate msAuthRestTemplate;

    @Autowired
    public JwtAuthorizationFilter(RestTemplate msAuthRestTemplate) {
        super(Config.class);

        this.msAuthRestTemplate = msAuthRestTemplate;
    }

    public static class Config {
        // TODO Put filter configuration here
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).getFirst();
            String jwt = authorizationHeader.replace("Bearer ", "");
            String url = "/jwt/validate?token=" + jwt;

            System.out.println("Calling " + url);

            boolean isValid = Boolean.TRUE.equals(msAuthRestTemplate.getForObject(url, Boolean.class));

            if (!isValid) {
                return onError(exchange, "Token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");

        String json = "{\"error\": \"" + errorMessage + "\"}";

        return response.writeWith(Mono.just(response.bufferFactory().wrap(json.getBytes())));
    }
}
