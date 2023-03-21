package com.javaproject.properties;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@ConstructorBinding
@Configuration("jwt")
public class JwtProperties {
    private String secretKey;
    private Long expiration;
    private Long refreshExpiration;
}
