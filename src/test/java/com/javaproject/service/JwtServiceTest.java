package com.javaproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;
    @Test
    public void secretKey() {
        String secretKey = jwtService.secret;
        System.out.println(secretKey);
    }
}