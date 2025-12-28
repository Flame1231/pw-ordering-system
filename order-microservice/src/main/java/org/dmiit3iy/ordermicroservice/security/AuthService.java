package org.dmiit3iy.ordermicroservice.security;

import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.mapper.AuthMapper;
import org.dmiit3iy.ordermicroservice.model.User;
import org.dmiit3iy.ordermicroservice.model.dto.AuthRequest;
import org.dmiit3iy.ordermicroservice.model.dto.AuthResponse;
import org.dmiit3iy.ordermicroservice.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AuthMapper authMapper;

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                authMapper.toAuthToken(request)
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}