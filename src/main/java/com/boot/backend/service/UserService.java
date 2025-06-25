package com.boot.backend.service;

import com.boot.backend.model.UserModel;
import com.boot.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        UserModel userModel=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found!"));
        return new User(userModel.getEmail(),userModel.getPassword(), List.of(new SimpleGrantedAuthority(userModel.getRole().name()))
        );
    }
}
