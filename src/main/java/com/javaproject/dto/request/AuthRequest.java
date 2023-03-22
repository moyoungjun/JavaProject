package com.javaproject.dto.request;

public record AuthRequest(
        String userId,
        String password
) {
}
