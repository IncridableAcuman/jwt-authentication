package com.server.demo.controller;

import com.server.demo.dto.UserRequest;
import com.server.demo.dto.UserResponse;
import com.server.demo.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> userList(){
        return ResponseEntity.ok(profileService.userList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(profileService.getUserById(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(@PathVariable Long id, @Valid @ModelAttribute UserRequest request){
        return ResponseEntity.ok(profileService.editUser(id,request));
    }
}
