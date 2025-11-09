package com.Incture.JavaFinalProject.Utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtCreation {

    private static final String SEC_KEY = "Javaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    // Get key for signing and validation
    public Key getKey() {
        return Keys.hmacShaKeyFor(SEC_KEY.getBytes());
    }

    // Generate JWT token
    public String JwtGeneration(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // lowercase "role"

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract claims from token
    public Claims extractToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract email directly from token
    public String extractEmail(String token) {
        return extractToken(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Claims claims = extractToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            System.out.println("❌ Token expired");
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            System.out.println("❌ Invalid JWT: " + e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.out.println("❌ JWT signature does not match");
        }
        return false;
    }
}
