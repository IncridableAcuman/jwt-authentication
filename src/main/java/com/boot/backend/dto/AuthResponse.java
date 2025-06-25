package com.boot.backend.dto;

import com.boot.backend.enums.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String accessToken;
    private String refreshToken;
    public AuthResponse(Long id,String username,String email,Role role,String accessToken,String refreshToken){
        this.id=id;
        this.username=username;
        this.email=email;
        this.role=role;
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
    }
}
