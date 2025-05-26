package com.microservice.auth_service.controller;

import com.microservice.auth_service.dto.AuthRequest;
import com.microservice.auth_service.dto.AuthResponse;
import com.microservice.auth_service.service.AuthService;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
@Component
public class AuthController {

    private final AuthService authService;

    // @RequiredArgsConstructor
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/test")
    public ResponseEntity<String> testSecureEndpoint() {
        return ResponseEntity.ok("You are authenticated and can access this secure endpoint!");
    }

    @GetMapping("/user/test")
    public ResponseEntity<String> userTest() {
        return ResponseEntity.ok("Hello USER");
    }

    @GetMapping("/admin/test")
    public ResponseEntity<String> testAdminAccess() {
        return ResponseEntity.ok("Hello Admin, you're authorized!");
    }



    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
