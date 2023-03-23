package com.javaproject.service;

import com.javaproject.entity.User;
import com.javaproject.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;

    @Value("${jwt.secretKey}")
    String secret;

    @Value("${jwt.expiration}")
    Long expiration;

    private SecretKey secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8));
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

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Timestamp.valueOf(currentDate))
                .setExpiration(Timestamp.valueOf(currentDate.plusSeconds(expiration)))
                .signWith(secretKey) //Signature
                .compact();
    }
    //토큰의 모든 정보 반환
    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
    public LocalDateTime getExpiration(String token) {
        Date expiration = getAllClaims(token).getExpiration();
        return expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
