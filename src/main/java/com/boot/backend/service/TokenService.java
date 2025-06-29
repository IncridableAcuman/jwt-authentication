package com.boot.backend.service;

import com.boot.backend.model.TokenModel;
import com.boot.backend.model.UserModel;
import com.boot.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    @Deprecated
    public TokenModel createToken(UserModel userModel,String refreshToken){
        TokenModel tokenModel=new TokenModel();
        tokenModel.setRefreshToken(refreshToken);
        tokenModel.setExpiryDate(LocalDateTime.now().plusDays(7));
        tokenModel.setExpired(false);
        tokenModel.setRevoked(false);
        tokenModel.setUserModel(userModel);
        return tokenRepository.save(tokenModel);
    }
    public  TokenModel findByUserModel(UserModel userModel){
        return tokenRepository.findByUserModel(userModel).orElseThrow(()->new RuntimeException("Token not found"));

    }
    public void removeToken(TokenModel tokenModel){
        tokenRepository.delete(tokenModel);
    }

    public void deleteToken(UserModel userModel){
       TokenModel tokenModel=findByUserModel(userModel);
       this.removeToken(tokenModel);
        tokenRepository.flush();
    }

}
