package com.microservices.cloud_gateway.security;

import com.microservies.cloud_gateway.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Skip auth endpoints (public)
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        // For /accounts/** and /files/** check Authorization header
        if (path.startsWith("/accounts") || path.startsWith("/files")) {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(TOKEN_PREFIX.length());

            if (!jwtUtil.validateToken(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }
}
