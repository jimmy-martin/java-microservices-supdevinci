package com.ms.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtService {
    public static Long defaultExpirationTimeInMs = 86400000L;
    public static SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public static String generateToken(String subject) {
        Instant now = Instant.now();

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(defaultExpirationTimeInMs)))
                .signWith(secretKey)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parse(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Claims decodeToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
}
