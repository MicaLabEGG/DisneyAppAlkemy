package com.example.DisneyAppAlkemy.controller;

import com.example.DisneyAppAlkemy.entity.AppUser;
import com.example.DisneyAppAlkemy.repository.AppUserRepository;
import com.example.DisneyAppAlkemy.security.jwt.JwtTokenUtil;
import com.example.DisneyAppAlkemy.security.payload.JwtResponse;
import com.example.DisneyAppAlkemy.security.payload.LoginRequest;
import com.example.DisneyAppAlkemy.security.payload.MessageResponse;
import com.example.DisneyAppAlkemy.security.payload.RegisterRequest;
import com.example.DisneyAppAlkemy.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;

    private final AppUserService appUserService;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authManager, AppUserService appUserService, PasswordEncoder encoder, JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.appUserService = appUserService;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest){
        //Check 1: username
        if (appUserService.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));

        }

        //Check 2: email
        if (appUserService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));

        }

        //Create new user´s account
        AppUser appUser = new AppUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        appUserService.save(appUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));

    }
}
