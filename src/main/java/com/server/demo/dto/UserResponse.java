package com.server.demo.dto;

import com.server.demo.enums.Role;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        Role role,
        boolean enabled,
        String avatar,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
