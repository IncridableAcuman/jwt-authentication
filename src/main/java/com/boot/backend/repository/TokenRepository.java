package com.boot.backend.repository;

import com.boot.backend.model.TokenModel;
import com.boot.backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenModel,Long> {
    Optional<TokenModel> findByExpiryDate(Date expiryDate);
    Optional<TokenModel> findByUserModel(UserModel userModel);
}
