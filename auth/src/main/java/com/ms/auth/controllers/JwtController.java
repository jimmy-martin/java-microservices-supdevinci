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
    public String generateToken(@RequestParam String id, @RequestParam String username) {
        return JwtService.generateToken(id, username);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return JwtService.validateToken(token);
    }

    @GetMapping("/decode")
    public Claims decodeToken(@RequestParam String token) {
        return JwtService.decodeToken(token);
    }
}
