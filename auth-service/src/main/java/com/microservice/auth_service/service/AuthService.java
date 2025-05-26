package com.microservice.auth_service.service;

import com.microservice.auth_service.dto.AuthRequest;
import com.microservice.auth_service.dto.AuthResponse;
import com.microservice.auth_service.model.User;
import com.microservice.auth_service.repository.UserRepository;
import com.microservice.auth_service.security.JwtUtil;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
@Component
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(AuthRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")  // uppercase role
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token);
    }
}
