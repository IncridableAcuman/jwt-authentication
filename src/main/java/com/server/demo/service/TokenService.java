package com.server.demo.service;

import com.server.demo.entity.Token;
import com.server.demo.entity.User;
import com.server.demo.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Optional<Token> findTokenByUser(User user){
         return tokenRepository.findByUser(user);
    }
    public void saveToken(User user,String refreshToken){
        findTokenByUser(user).ifPresent(tokenRepository::delete);
        Token token = new Token();
        token.setUser(user);
        token.setRefreshToken(refreshToken);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        tokenRepository.save(token);
    }
}
