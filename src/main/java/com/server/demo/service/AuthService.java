package com.server.demo.service;

import com.server.demo.dto.*;
import com.server.demo.entity.User;
import com.server.demo.exception.UnAuthorizationExceptionHandler;
import com.server.demo.util.CookieUtil;
import com.server.demo.util.JwtUtil;
import com.server.demo.util.MailUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
private final UserService userService;
private final TokenService tokenService;
private final CookieUtil cookieUtil;
private final JwtUtil jwtUtil;
private final MailUtil mailUtil;

    @Transactional
    public AuthResponse authResponse(User user,HttpServletResponse response){
        Map<String,String> tokens = jwtUtil.getTokens(user);
        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");
        tokenService.saveToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return userService.authResponse(user,accessToken);
    }

    @Transactional
    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        userService.existUser(request.getEmail());
        User user = userService.create(request);
        return authResponse(user,response);
    }
    @Transactional
    public AuthResponse login(LoginRequest request,HttpServletResponse response){
        User user = userService.findUserByEmail(request.getEmail());
        user.setEnabled(true);
        user=userService.saveUser(user);
        userService.isPasswordEqual(request.getPassword(), user.getPassword());
        return authResponse(user,response);
    }
    @Transactional
    public AuthResponse refresh(String refreshToken,HttpServletResponse response){
        if (!jwtUtil.validateToken(refreshToken)){
            throw new UnAuthorizationExceptionHandler("Token is missing or invalid.");
        }
        String email = jwtUtil.getSubjectFromToken(refreshToken);
        User user = userService.findUserByEmail(email);
        user.setEnabled(true);
        user=userService.saveUser(user);
        return authResponse(user,response);
    }
    @Transactional
    public void logout(String refreshToken,HttpServletResponse response){
        String email = jwtUtil.getSubjectFromToken(refreshToken);
        User user = userService.findUserByEmail(email);
        user.setEnabled(false);
        user = userService.saveUser(user);
        cookieUtil.clearCookie(response);
        tokenService.removeToken(user);
    }
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request){
        User user = userService.findUserByEmail(request.getEmail());
        String token = jwtUtil.getTokens(user).get("accessToken");
        String url = "http://localhost:5173/reset-password?token="+token;
        mailUtil.sendMail(user.getEmail(),"Reset Password",url);
    }
    @Transactional
    public void resetPassword(ResetPasswordRequest request){
        if (!jwtUtil.validateToken(request.getToken())){
            throw new UnAuthorizationExceptionHandler("Token is missing or invalid.");
        }
        String email = jwtUtil.getSubjectFromToken(request.getToken());
        User user = userService.findUserByEmail(email);
        userService.updatePassword(user,request.getPassword());
    }
    public UserResponse getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication !=null;
        User user = (User) authentication.getPrincipal();
        assert user !=null;
        return userService.userResponse(user);
    }
}
