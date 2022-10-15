package com.example.DisneyAppAlkemy.repository;

import com.example.DisneyAppAlkemy.entity.Genders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GendersRepository extends JpaRepository<Genders, Integer> {

    public Optional<Genders> findById(Integer genreId);
    public Genders getById(Integer genderId);
}
