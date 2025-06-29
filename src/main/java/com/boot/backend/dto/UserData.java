package com.boot.backend.dto;

import com.boot.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private Long id;
    private String username;
    private Role role;
}
