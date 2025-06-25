package com.boot.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
//    email
    @NotBlank(message = "Email must be required!")
    @Email
    private String email;
//    password
    @NotBlank(message = "Password must be required!")
    private String password;
}
