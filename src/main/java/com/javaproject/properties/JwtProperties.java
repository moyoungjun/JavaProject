package com.javaproject.properties;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@ConstructorBinding
@Configuration("jwt")
public class JwtProperties {
    public String secretKey;
    public Long expiration;
    public Long refreshExpiration;
}
