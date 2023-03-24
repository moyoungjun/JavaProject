package com.javaproject.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public record AccountResponse(
        Long id,
        String userId,
        String userName,
        Collection<? extends GrantedAuthority> authorities
) {

}
