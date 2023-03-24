package com.javaproject.dto.request;

import com.javaproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest{
    String userId;
    String password;
    String passwordConfirm;
    String userName;
    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .username(userName)
                .authorities(Set.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
