package com.server.demo.service;

import com.server.demo.dto.UserRequest;
import com.server.demo.dto.UserResponse;
import com.server.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserService userService;
    private final FileService fileService;

    @Transactional
    public List<UserResponse> userList(){
        return userService.userList();
    }
    @Transactional
    public UserResponse getUserById(Long id){
        User user = userService.findUserById(id);
        return userService.userResponse(user);
    }
    @Transactional
    public UserResponse editUser(Long id, UserRequest request){
        User user = userService.findUserById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setAvatar(fileService.saveFile(request.getAvatar()));
        user = userService.saveUser(user);
        return userService.userResponse(user);
    }
}
