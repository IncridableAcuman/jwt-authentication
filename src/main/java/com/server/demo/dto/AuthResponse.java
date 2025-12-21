package com.server.demo.dto;

public record AuthResponse(
        Long id,
        String username,
        String email,
        String accessToken
) {
}
