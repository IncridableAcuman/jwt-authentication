package com.server.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRequest {
    @NotBlank(message = "Firstname must be required")
    @Size(min = 3,max = 255,message = "The firstname must be between 3 and 255 characters long.")
    private String firstName;

    @NotBlank(message = "Lastname must be required")
    @Size(min = 3,max = 255,message = "The lastname must be between 3 and 255 characters long.")
    private String lastName;

    @NotBlank(message = "Username must be required")
    @Size(min = 3,max = 255,message = "The username must be between 3 and 255 characters long.")
    private String username;

    @NotNull
    private MultipartFile avatar;
}
