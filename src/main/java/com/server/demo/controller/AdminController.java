package com.server.demo.controller;

import com.server.demo.dto.UserResponse;
import com.server.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully: "+id);
    }
    @PatchMapping("/users/role/{id}")
    public ResponseEntity<UserResponse> changeRole(@PathVariable Long id){
        return ResponseEntity.ok(adminService.editRole(id));
    }
}
