package com.example.DisneyAppAlkemy.services;

import com.example.DisneyAppAlkemy.entity.Characters;
import com.example.DisneyAppAlkemy.entity.Genders;
import com.example.DisneyAppAlkemy.entity.Movies;
import com.example.DisneyAppAlkemy.repository.GendersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GendersServices {

    @Autowired
    private GendersRepository gendersRepository;

    public Genders save(Genders gender){
        return gendersRepository.save(gender);
    }

    public boolean delete(Integer genreId){
        try{
            gendersRepository.deleteById(genreId);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public List<Genders> getAllGenders(){
        return gendersRepository.findAll();
    }

    public Optional<Genders> findById(Integer genreId) {
        return gendersRepository.findById(genreId);
    }

    public List<Movies> getMoviesByGenreId(Integer genreId) {
        Genders gender = gendersRepository.getById(genreId);
        if(gender != null){
            List<Movies> movies = gender.getMoviesId();
            return movies;
        }else{
            return null;
        }

    }
}
