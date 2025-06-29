package com.boot.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequest {
    @NotBlank(message = "Token must be required!")
    private String refreshToken;
}
