package com.example.DisneyAppAlkemy.repository;

import com.example.DisneyAppAlkemy.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public AppUser findByUsername(String username);
}
