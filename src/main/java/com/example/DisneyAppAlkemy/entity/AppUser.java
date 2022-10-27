package com.example.DisneyAppAlkemy.entity;


import com.example.DisneyAppAlkemy.enums.AppUserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Entity
@Data
@NoArgsConstructor
public class AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String email;
    private String password;

    public AppUser(String username, String email, String encode) {
    }
}

