package com.javaproject.dto.response;

import com.javaproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String userId;
    private String userName;

    public UserResponse toUserResponse(User user) {
        return new UserResponse(userId = user.getUserId(), userName = user.getUsername());
    }
}
