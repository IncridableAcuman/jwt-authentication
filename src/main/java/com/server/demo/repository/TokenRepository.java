package com.server.demo.repository;

import com.server.demo.entity.Token;
import com.server.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByUser(User user);
}
