package com.example.DisneyAppAlkemy.services;

import com.example.DisneyAppAlkemy.entity.Movies;
import com.example.DisneyAppAlkemy.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServices {

    @Autowired
    private MoviesRepository movieRepository;

    public List<Movies> findAll(){
        return movieRepository.findAll();
    }

    public Optional<Movies> findById(Integer movieId){
        return movieRepository.findById(movieId);
    }
    public Movies save(Movies movie) {
        return movieRepository.save(movie);
    }
    public List<Movies> findByTitle(String title){
        return movieRepository.findByTitle(title);
    }

    public List<Movies> getByOrder(String order){
        if(order.equals("ASC")){
            return movieRepository.getAllByOrderASC();
        }else if(order.equals("DESC")){
            return movieRepository.getAllByOrderDESC();
        }else{
            return movieRepository.findAll();
        }
    }

    public boolean delete(Integer movieId){
        try{
            movieRepository.deleteById(movieId);
            return true;
        }catch(Exception err){
            return false;
        }

    }
}
