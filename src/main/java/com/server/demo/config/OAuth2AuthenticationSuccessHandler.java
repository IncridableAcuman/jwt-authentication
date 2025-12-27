package com.server.demo.config;

import com.server.demo.entity.User;
import com.server.demo.service.TokenService;
import com.server.demo.service.UserService;
import com.server.demo.util.CookieUtil;
import com.server.demo.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    @Override
    public void onAuthenticationSuccess(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        assert oAuth2User != null;
        String email =  oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User user = userService.findByEmailOptional(email).orElseGet(()->userService.createFromOAuth(email,name,oAuth2User.getAttributes()));
        Map<String,String> tokens = jwtUtil.getTokens(user);
        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        tokenService.saveToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);

        String redirectUrl = "http://localhost:5173/oauth2/redirect?token=" + accessToken;
        response.sendRedirect(redirectUrl);
    }
}
