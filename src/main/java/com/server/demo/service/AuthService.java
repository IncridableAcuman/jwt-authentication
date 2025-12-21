package com.server.demo.service;

import com.server.demo.dto.AuthResponse;
import com.server.demo.dto.LoginRequest;
import com.server.demo.dto.RegisterRequest;
import com.server.demo.entity.User;
import com.server.demo.util.CookieUtil;
import com.server.demo.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
private final UserService userService;
private final TokenService tokenService;
private final CookieUtil cookieUtil;
private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        userService.existUser(request.getEmail());
        User user = userService.create(request);
        String accessToken = jwtUtil.getTokens(user).get("accessToken");
        String refreshToken = jwtUtil.getTokens(user).get("refreshToken");
        tokenService.saveToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return userService.authResponse(user,accessToken);
    }
    @Transactional
    public AuthResponse login(LoginRequest request,HttpServletResponse response){
        User user = userService.findUserByEmail(request.getEmail());
        userService.isPasswordEqual(request.getPassword(), user.getPassword());
        String accessToken = jwtUtil.getTokens(user).get("accessToken");
        String refreshToken = jwtUtil.getTokens(user).get("refreshToken");
        tokenService.saveToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return userService.authResponse(user,accessToken);
    }
}
