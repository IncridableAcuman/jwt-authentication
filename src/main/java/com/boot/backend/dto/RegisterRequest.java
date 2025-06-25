package com.boot.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    // username
    @NotBlank(message = "Username must be required!")
    @Size(min = 3,max = 50,message = "Username must between 3 and 50 characters!")
    private String username;
    //    email
    @NotBlank(message = "Email must be required!")
    @Email
    private String email;
    //    password
    @NotBlank(message = "Password must be required!")
    private String password;
}
