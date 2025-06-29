package com.boot.backend.service;

import com.boot.backend.dto.RegisterRequest;
import com.boot.backend.enums.Role;
import com.boot.backend.model.UserModel;
import com.boot.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserModel createUser(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User already exist");
        }
        UserModel userModel=new UserModel();
        userModel.setUsername(request.getUsername());
        userModel.setEmail(request.getEmail());
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        userModel.setRole(Role.USER);
        return userRepository.save(userModel);
    }
//    find user
    public UserModel findUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
    }
    public void matchesPassword(String requestPassword,String userPassword){
        if(!passwordEncoder.matches(requestPassword,userPassword)){
            throw new RuntimeException("Password does not match");
        }
    }
    public void updatePassword(String password){
        UserModel userModel=new UserModel();
        userModel.setPassword(passwordEncoder.encode(password));
         userRepository.save(userModel);
    }
}
