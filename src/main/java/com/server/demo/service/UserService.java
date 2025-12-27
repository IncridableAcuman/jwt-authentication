package com.server.demo.service;

import com.server.demo.dto.AuthResponse;
import com.server.demo.dto.RegisterRequest;
import com.server.demo.dto.UserResponse;
import com.server.demo.entity.User;
import com.server.demo.enums.Role;
import com.server.demo.exception.BadRequestExceptionHandler;
import com.server.demo.exception.NotFoundExceptionHandler;
import com.server.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundExceptionHandler("User not found"));
    }
    @Transactional
    public User create (RegisterRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setAvatar("https://github.com/shadcn.png");
        user.setCreateAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    @Transactional
    public void existUser(String email){
        if (userRepository.findByEmail(email).isPresent()){
            throw new BadRequestExceptionHandler("User already exist.");
        }
    }
    public void isPasswordEqual(String rawPassword,String encodePassword){
        if (!passwordEncoder.matches(rawPassword,encodePassword)){
            throw new BadRequestExceptionHandler("Password does not match.");
        }
    }
    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public UserResponse userResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isEnabled(),
                user.getAvatar(),
                user.getCreateAt(),
                user.getUpdatedAt()
        );
    }
    @Transactional
   public User changeRole(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("User not found"));
        if (user.getRole().equals(Role.USER)){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        return saveUser(user);
   }
   public AuthResponse authResponse(User user,String accessToken){
        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                accessToken
        );
   }
   @Transactional
   public void updatePassword(User user,String password){
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
   }
   @Transactional
   public List<UserResponse> userList(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userResponse).toList();
   }
   @Transactional
    public User findUserById(Long id){
       return userRepository.findById(id).orElseThrow(()->new NotFoundExceptionHandler("User not found"));
   }
   @Transactional
    public void deleteUser(Long id){
        User user = findUserById(id);
        userRepository.delete(user);
   }
}
