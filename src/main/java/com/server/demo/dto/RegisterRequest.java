package com.server.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Firstname must be required")
    @Size(min = 3,max = 255,message = "The firstname must be between 3 and 255 characters long.")
    private String firstName;

    @NotBlank(message = "Lastname must be required")
    @Size(min = 3,max = 255,message = "The lastname must be between 3 and 255 characters long.")
    private String lastName;

    @NotBlank(message = "Username must be required")
    @Size(min = 3,max = 255,message = "The username must be between 3 and 255 characters long.")
    private String username;

    @NotBlank(message = "Email must be required")
    @Email
    private String email;

    @NotBlank(message = "Password must be required")
    @Size(min = 8,max = 255,message = "Password must be between 8 and 255 characters long.")
    private String password;
}
