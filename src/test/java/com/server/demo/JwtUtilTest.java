package com.server.demo;

import com.server.demo.entity.User;
import com.server.demo.enums.Role;
import com.server.demo.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("coder");
        user.setEmail("test@gmail.com");
        user.setRole(Role.USER);
    }
    @Test
    void shouldGenerateAccessAndRefreshToken(){
        Map<String,String> tokens = jwtUtil.getTokens(user);

        assertNotNull(tokens.get("accessToken"));
        assertNotNull(tokens.get("refreshToken"));

    }
}
