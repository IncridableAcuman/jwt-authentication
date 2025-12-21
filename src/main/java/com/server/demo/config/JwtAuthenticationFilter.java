package com.server.demo.config;

import com.server.demo.service.UserDetailsService;
import com.server.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain
                    filterChain
    ) throws ServletException, IOException {
     String authHeader = request.getHeader("Authorization");
     String jwt=null;
     String email=null;
     if (authHeader != null && authHeader.startsWith("Bearer ")){
         jwt = authHeader.substring(7);
         email = jwtUtil.getSubjectFromToken(jwt);
     }
     if (email!=null && SecurityContextHolder.getContext().getAuthentication() == null){
         UserDetails userDetails = userDetailsService.loadUserByUsername(email);
         if (!jwtUtil.validateToken(jwt)){
             UsernamePasswordAuthenticationToken authToken =
                     new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(authToken);
         }
     }
     filterChain.doFilter(request,response);
    }
}
