package com.boot.backend.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CookieUtil {
    @Value("${jwt.refresh-time}")
    private  int refreshTime;
//    add token to cookie
    public  void addCookie(HttpServletResponse response,String refreshToken){
        Cookie cookie=new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(refreshTime);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
//   remove token with cookie
    public  void removeToken(HttpServletResponse response){
        Cookie cookie=new Cookie("refreshToken",null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
