package com.ms.auth.controllers;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.auth.services.JwtService;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    @GetMapping("/generate")
    public String generateToken(@RequestParam String username) {
        System.out.println("Generating token for " + username);
        return JwtService.generateToken(username);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        System.out.println("Validating token " + token);
        return JwtService.validateToken(token);
    }

    @GetMapping("/decode")
    public Claims decodeToken(@RequestParam String token) {
        return JwtService.decodeToken(token);
    }
}
