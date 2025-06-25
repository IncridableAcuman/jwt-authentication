package com.boot.backend.util;

import com.boot.backend.model.UserModel;
import com.boot.backend.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@RequiredArgsConstructor
public class JwtUtil {
    private final TokenRepository tokenRepository;
//    secret
    @Value("${jwt.secret}")
    private String secret;
//    access time
    @Value("${jwt.access-time}")
    private long accessTime;
//    refresh time
    @Value("${jwt.refresh-time}")
    private long refreshTime;

//    get signing key
    public Key getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
//    generate access token
    @Deprecated
    public String generateAccessToken(UserModel userModel){
        return Jwts
                .builder()
                .setSubject(userModel.getEmail())
                .claim("id",userModel.getId())
                .claim("username",userModel.getUsername())
                .claim("role",userModel.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+accessTime))
                .signWith(getSigningKey())
                .compact();
    }
//    generate refresh token
    @Deprecated
    public String refreshToken(UserModel userModel){
        return Jwts
                .builder()
                .setSubject(userModel.getEmail())
                .claim("id",userModel.getId())
                .claim("username",userModel.getUsername())
                .claim("role",userModel.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+refreshTime))
                .signWith(getSigningKey())
                .compact();
    }
//    validate token
    @Deprecated
    public Boolean validateToken(String refreshToken){
        try {
            Jwts.parser().setSigningKey(getSigningKey()).build().parseSignedClaims(refreshToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    extract email with token
    @Deprecated
    public String extractEmail(String refreshToken){
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(refreshToken)
                .getBody().getSubject();
    }
//    extract expiration

}
