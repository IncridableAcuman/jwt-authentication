package com.boot.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    refresh token
    @Column(nullable = false,unique = true)
    private String refreshToken;

    //    refresh token of expiry date
    private LocalDateTime expiryDate;

    private boolean expired;
    private boolean revoked;

    //    user
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

}

