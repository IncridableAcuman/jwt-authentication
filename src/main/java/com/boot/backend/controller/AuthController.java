package com.boot.backend.controller;

import com.boot.backend.dto.*;
import com.boot.backend.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    register
    @Deprecated
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request, HttpServletResponse response){
        return ResponseEntity.ok(authService.register(request,response));
    }
//    login
    @Deprecated
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request,HttpServletResponse response){
        return ResponseEntity.ok(authService.login(request,response));
    }
//    refresh
    @Deprecated
    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@CookieValue(name = "refreshToken") String refreshToken,HttpServletResponse response){
        return ResponseEntity.ok(authService.refresh(refreshToken,response));
    }
//    logout
    @Deprecated
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(name = "refreshToken") String refreshToken,HttpServletResponse response){
        authService.logout(refreshToken,response);
        return ResponseEntity.ok("User logged out");
    }
//    forgot password
    @Deprecated
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request){
        return ResponseEntity.ok(authService.forgotPassword(request));
    }
//    reset password
    @Deprecated
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPassword resetPassword){
        return ResponseEntity.ok(authService.resetPassword(resetPassword));
    }
    @Deprecated
    @GetMapping("/user-data")
    public ResponseEntity<UserData> getUserData(){
        return ResponseEntity.ok(authService.getUserData());
    }
}
