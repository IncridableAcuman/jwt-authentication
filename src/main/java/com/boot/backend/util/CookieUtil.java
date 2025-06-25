package com.boot.backend.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

public class CookieUtil {
    @Value("${jwt.refresh-time}")
    private long refreshTime;
//    add token to cookie
    public static void addCookie(HttpServletResponse response,String refreshToken){
        Cookie cookie=new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(604800000);
        cookie.setSecure(false);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
//   remove token with cookie
    public static void removeToken(HttpServletResponse response){
        Cookie cookie=new Cookie("refreshToken",null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setSecure(false);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
//    get token with cookie
    public static String getCookieValue(HttpServletRequest request){
        if(request.getCookies()!=null){
            for(Cookie cookie:request.getCookies()){
                if(cookie.getName().equals("refreshToken")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
