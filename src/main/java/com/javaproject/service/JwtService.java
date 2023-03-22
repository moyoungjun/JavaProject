package com.javaproject.service;

import com.javaproject.entity.User;
import com.javaproject.properties.JwtProperties;
import com.javaproject.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private JwtProperties jwtProperties;
    private UserRepository userRepository;


    public SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createJWT(User user) {
        //Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //Payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", user.getUsername());


        LocalDateTime currentDate = LocalDateTime.now();
        Long expiration = jwtProperties.expiration;

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Timestamp.valueOf(currentDate))
                .setExpiration(Timestamp.valueOf(currentDate.plusSeconds(expiration)))
                .signWith(this.secretKey) //Signature
                .compact();
    }

}
