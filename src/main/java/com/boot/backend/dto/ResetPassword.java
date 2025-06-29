package com.boot.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPassword {
    @NotBlank(message = "Password must be required!")
    private String password;
    @NotBlank
    private String refreshToken;
}
