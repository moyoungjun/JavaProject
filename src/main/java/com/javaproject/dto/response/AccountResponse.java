package com.javaproject.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;


public record AccountResponse(
        Long id,
        String userId,
        String userName,
        Set<String> authorities
) {

}
