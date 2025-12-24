package com.server.demo.util;

import com.server.demo.entity.User;
import com.server.demo.exception.UnAuthorizationExceptionHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access_time}")
    private Long accessTime;
    @Value("${jwt.refresh_time}")
    private Long refreshTime;
    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private String generateToken(User user,Long expiryTime){
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .claim("username",user.getUsername())
                .claim("id",user.getId())
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiryTime))
                .signWith(key)
                .compact();
    }
    public Map<String,String> getTokens(User user){
        String accessToken = generateToken(user,accessTime);
        String refreshToken = generateToken(user,refreshTime);
        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken",accessToken);
        tokens.put("refreshToken",refreshToken);
        return tokens;
    }
    public Claims extractClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getSubjectFromToken(String token){
        try {
            return extractClaims(token).getSubject();
        } catch (Exception e){
            throw new UnAuthorizationExceptionHandler(e.getMessage());
        }
    }
    public Date getExpirationTimeFromToken(String token){
        return extractClaims(token).getExpiration();
    }
    public boolean validateToken(String token){
        try {
            Date expiryDate = getExpirationTimeFromToken(token);
            return expiryDate.after(new Date());
        } catch (
                SecurityException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException e
        ){
            throw new UnAuthorizationExceptionHandler(e.getMessage());
        }
    }
}
