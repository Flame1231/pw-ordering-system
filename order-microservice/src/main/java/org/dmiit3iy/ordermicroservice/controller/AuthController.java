package org.dmiit3iy.ordermicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.model.dto.AuthRequest;
import org.dmiit3iy.ordermicroservice.model.dto.AuthResponse;
import org.dmiit3iy.ordermicroservice.model.dto.RegistrationRequest;
import org.dmiit3iy.ordermicroservice.security.AuthService;
import org.dmiit3iy.ordermicroservice.security.jwt.JwtService;
import org.dmiit3iy.ordermicroservice.service.UserDetailsServiceImpl;
import org.dmiit3iy.ordermicroservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
            String result = userService.registerUser(request);
            return ResponseEntity.ok(result);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}