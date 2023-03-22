package com.javaproject.controller;

import com.javaproject.dto.request.AuthRequest;
import com.javaproject.dto.response.AuthResponse;
import com.javaproject.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @ApiOperation("유저 토큰")
    @PostMapping
    public AuthResponse authorize(@RequestBody AuthRequest authRequest) {
        return authService.authorize(authRequest);
    }
}
