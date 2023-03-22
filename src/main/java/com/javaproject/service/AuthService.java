package com.javaproject.service;

import com.javaproject.dto.request.AuthRequest;
import com.javaproject.dto.response.AuthResponse;
import com.javaproject.entity.User;
import com.javaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public final UserRepository userRepository;

    public AuthResponse authorize(AuthRequest authRequest) {
        User user = userRepository.findByUserId(authRequest.userId());
        if(!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            System.out.println("틀림");
        }
        String token = jwtService.createJWT(user);
        return new AuthResponse("berear",token,jwtService.getExpiration(token));
    }
}
