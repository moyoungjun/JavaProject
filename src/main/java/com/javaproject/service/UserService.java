package com.javaproject.service;

import com.javaproject.dto.request.AccountRequest;
import com.javaproject.dto.response.AccountResponse;
import com.javaproject.entity.User;
import com.javaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    //TODO Refactoring Validation and authorities
    public AccountResponse CreationUser(AccountRequest accountRequest) {
        String encode = passwordEncoder.encode(accountRequest.getPassword());
        accountRequest.encodePassword(encode);
        User user = accountRequest.toEntity();
        userRepository.save(user);

        return new AccountResponse(user.getId(),user.getUsername(),user.getName(),user.getAuthorities());
        }
}
