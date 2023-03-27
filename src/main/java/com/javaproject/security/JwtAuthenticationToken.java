package com.javaproject.security;

import org.hibernate.mapping.Any;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Any principal;
    private String credentials;

    public JwtAuthenticationToken(Any principal, String credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }
    public JwtAuthenticationToken(Any principal, String credentials,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }


    @Override
    public String getCredentials() {
        return this.credentials;
    }

    @Override
    public Any getPrincipal() {
        return this.principal;
    }
}
