package com.javaproject.service;

import com.javaproject.common.BaseException;
import com.javaproject.dto.request.AuthRequest;
import com.javaproject.dto.response.AuthResponse;
import com.javaproject.entity.User;
import com.javaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.javaproject.common.ErrorCode.INVALID_ID_PASSWORD;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthResponse authorize(AuthRequest authRequest) {
        User user = userRepository.findByUserId(authRequest.userId());
        if(!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new BaseException(INVALID_ID_PASSWORD);
        }
        String token = jwtService.createJWT(user);
        return new AuthResponse("berear",token,jwtService.getExpiration(token));
    }
}
