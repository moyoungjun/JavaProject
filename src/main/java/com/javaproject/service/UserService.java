package com.javaproject.service;

import com.javaproject.common.BaseException;
import com.javaproject.dto.request.AccountRequest;
import com.javaproject.dto.response.AccountResponse;
import com.javaproject.dto.response.UserResponse;
import com.javaproject.entity.User;
import com.javaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.javaproject.common.ErrorCode.CONFIRM_PASSWORD;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserResponse getUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(Exception::new);
        return new UserResponse(user.getUserId(), user.getUsername());
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("this");
        return users;
    }

    public AccountResponse CreationUser(AccountRequest accountRequest) {
        //비밀번호 일치 여부 확인
        if(!accountRequest.getPassword().matches(accountRequest.getPasswordConfirm())){
            throw new BaseException(CONFIRM_PASSWORD);
        }
        String encode = passwordEncoder.encode(accountRequest.getPassword());
        accountRequest.encodePassword(encode);
        User user = accountRequest.toEntity();
        userRepository.save(user);
        Set<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new AccountResponse(user.getId(), user.getUsername(), user.getName(), authorities);
    }
}
