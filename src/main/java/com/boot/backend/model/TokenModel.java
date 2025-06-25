package com.boot.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    @Column(nullable = false)
    private String refreshToken;
    //    user
    @ManyToOne
    private UserModel userModel;
//    refresh token of expiry date
    private Date expiryDate;

}

