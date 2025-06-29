package com.boot.backend.service;

import com.boot.backend.dto.*;
import com.boot.backend.model.TokenModel;
import com.boot.backend.model.UserModel;
import com.boot.backend.util.CookieUtil;
import com.boot.backend.util.JwtUtil;
import com.boot.backend.util.MessageUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CookieUtil cookieUtil;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;
    private final MessageUtil messageUtil;
//    register
    @Transactional
    @Deprecated
    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        UserModel userModel=userService.createUser(request);
//        generate tokens
        String accessToken=jwtUtil.generateAccessToken(userModel);
        String refreshToken=jwtUtil.generateRefreshToken(userModel);
//        saving token
        tokenService.createToken(userModel,refreshToken);
        // add refresh token to cookie
        cookieUtil.addCookie(response,refreshToken);
        return new AuthResponse(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getRole(),
                accessToken,refreshToken
                );
    }
//    login
    @Deprecated
    @Transactional
    public AuthResponse login(AuthRequest request ,HttpServletResponse response){
        UserModel user=userService.findUser(request.getEmail());
//        equals password
        userService.matchesPassword(request.getPassword(), user.getPassword());
        tokenService.findByUserModel(user);
       tokenService.deleteToken(user);
        String accessToken=jwtUtil.generateAccessToken(user);
        String refreshToken=jwtUtil.generateRefreshToken(user);
        tokenService.createToken(user,refreshToken);
        // add refresh token to cookie
        cookieUtil.addCookie(response,refreshToken);
        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                accessToken,refreshToken
        );
    }
//    refresh
    @Transactional
    @Deprecated
    public AuthResponse refresh(String refreshToken ,HttpServletResponse response){
//        validate token
        if(!jwtUtil.validateToken(refreshToken)){
            throw new RuntimeException("Invalid token");
        }
//        extracting email with token
        String email=jwtUtil.extractEmail(refreshToken);
        UserModel userModel=userService.findUser(email);
//        delete token and generate tokens
        tokenService.findByUserModel(userModel);
        tokenService.deleteToken(userModel);
        String accessToken=jwtUtil.generateAccessToken(userModel);
        String newRefreshToken=jwtUtil.generateRefreshToken(userModel);

        tokenService.createToken(userModel,newRefreshToken);
        // add refresh token to cookie
        cookieUtil.addCookie(response,newRefreshToken);
        return new AuthResponse(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getRole(),
                accessToken,newRefreshToken
        );
    }
    @Deprecated
    @Transactional
    public void logout(String refreshToken,HttpServletResponse response){
        //        validate token
        if(!jwtUtil.validateToken(refreshToken)){
            throw new RuntimeException("Invalid token");
        }
        String email=jwtUtil.extractEmail(refreshToken);
        UserModel userModel=userService.findUser(email);
        TokenModel tokenModel=tokenService.findByUserModel(userModel);
        tokenService.removeToken(tokenModel);
        cookieUtil.removeToken(response);
    }
//    forgot password
    @Deprecated
    @Transactional
    public String forgotPassword(ForgotPasswordRequest request){
        UserModel userModel=userService.findUser(request.getEmail());
        String accessToken=jwtUtil.generateAccessToken(userModel);
        messageUtil.sendMail(request.getEmail(), "Reset Password","http://localhost:5173?accessToken="+accessToken);
        return "Reset Password link sent to your email";
    }
    @Deprecated
    @Transactional
    public String resetPassword(ResetPassword resetPassword){
        if(!jwtUtil.validateToken(resetPassword.getRefreshToken())){
            throw new RuntimeException("Invalid token");
        }
        String email= jwtUtil.extractEmail(resetPassword.getRefreshToken());
        if(email==null || email.isEmpty()){
            throw new RuntimeException("Email is empty");
        }
        UserModel userModel=userService.findUser(email);
        userService.updatePassword(userModel.getPassword());
        return "Password updated successfully";
    }
//    get user data
    public UserData getUserData(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel=(UserModel) authentication.getPrincipal();
       return new UserData(userModel.getId(),userModel.getUsername(),userModel.getRole());
    }
}
