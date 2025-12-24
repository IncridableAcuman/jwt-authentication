package com.server.demo.service;

import com.server.demo.dto.UserResponse;
import com.server.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;

    @Transactional
    public void deleteUser(Long id){
        userService.deleteUser(id);
    }
    @Transactional
    public UserResponse editRole(Long id){
        User user = userService.changeRole(id);
        return userService.userResponse(user);
    }
}
