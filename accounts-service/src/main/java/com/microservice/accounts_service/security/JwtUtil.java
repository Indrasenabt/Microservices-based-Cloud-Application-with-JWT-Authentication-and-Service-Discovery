package com.microservice.accounts_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "secret-key";
    private final long EXPIRATION_TIME = 86400000; // 1 day
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    public String extractUsername(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }

    public String extractRole(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("role").asString();
    }

    public boolean validateToken(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

