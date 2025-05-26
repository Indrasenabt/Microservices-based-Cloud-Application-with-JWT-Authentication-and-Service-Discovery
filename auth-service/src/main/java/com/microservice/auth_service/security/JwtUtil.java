package com.microservice.auth_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "secret-key";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtUtil() {
        this.algorithm = Algorithm.HMAC256(SECRET);
        this.verifier = JWT.require(algorithm).build();
    }

    public String generateToken(String username, String role) {
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public boolean validateToken(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }

    // Optional: combines validate and extract in one method
    public String validateTokenAndRetrieveSubject(String token) {
        return verifier.verify(token).getSubject();
    }

    public String extractRole(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
        return decodedJWT.getClaim("role").asString();
    }


}
