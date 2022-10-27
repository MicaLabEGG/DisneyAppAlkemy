package com.example.DisneyAppAlkemy.services;

import com.example.DisneyAppAlkemy.entity.AppUser;
import com.example.DisneyAppAlkemy.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         com.example.DisneyAppAlkemy.entity.AppUser appUser = appUserRepository.findByUsername(username).
    }


    public void save (AppUser appUser){
        appUserRepository.save(appUser);
    }

    public boolean existsByUsername(String username){
       return appUserRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return appUserRepository.existsByEmail(email);
    }
}