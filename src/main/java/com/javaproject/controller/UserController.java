package com.javaproject.controller;

import com.javaproject.dto.request.AccountRequest;
import com.javaproject.dto.response.AccountResponse;
import com.javaproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public AccountResponse CreationUser(@RequestBody AccountRequest accountRequest) {
        return userService.CreationUser(accountRequest);
    }
}
