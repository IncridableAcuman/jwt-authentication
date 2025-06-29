package com.boot.backend.model;

import com.boot.backend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;//user,admin

//    user details
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }
//    isAccountNonExpired
    public boolean isAccountNonExpired(){
        return true;
    }
//    isAccountNonLocked
    public boolean isAccountNonLocked(){
        return true;
    }
//    isCredentialsNonExpired
    public boolean isCredentialsNonExpired(){
        return true;
    }
//    isEnabled
    public boolean isEnabled(){
        return true;
    }
}
