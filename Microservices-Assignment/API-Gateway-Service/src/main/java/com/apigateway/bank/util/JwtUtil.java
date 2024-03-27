package com.apigateway.bank.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

// Utility class for handling JWT operations
@Component
public class JwtUtil {

    // Secret key for JWT signing and verification
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // Method to validate a JWT token
    public void validateToken(final String token) {
        // Parse and validate the token using the signing key
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    // Method to retrieve the signing key
    private Key getSignKey() {
        // Decode the secret key bytes from Base64 and create a signing key
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
