package com.nt.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;
    
	// Secret key (keep it long)
    //private static final String SECRET ="MySecretKeyForJWTAuthenticationInSpringBootProject123456789";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Generate Token
    public String generateToken(String username) {

        return Jwts.builder().subject(username).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith((SecretKey)getSignKey())
                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {
        Claims claims = Jwts.parser().verifyWith((SecretKey) getSignKey())
                .build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    // Validate Token
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token).getExpiration().before(new Date());
	}

	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith((SecretKey) getSignKey())
                   .build().parseSignedClaims(token).getPayload();
	}
}
