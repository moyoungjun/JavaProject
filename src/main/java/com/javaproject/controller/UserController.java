package com.javaproject.controller;

import com.javaproject.dto.request.AccountRequest;
import com.javaproject.dto.response.AccountResponse;
import com.javaproject.dto.response.UserResponse;
import com.javaproject.entity.User;
import com.javaproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse test(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> test2() {
        return userService.getUsers();
    }

    @PostMapping
    public AccountResponse CreationUser(@RequestBody AccountRequest accountRequest) {
        return userService.CreationUser(accountRequest);
    }
}
