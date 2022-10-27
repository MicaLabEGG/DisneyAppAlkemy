package com.example.DisneyAppAlkemy.controller;

import com.example.DisneyAppAlkemy.entity.Characters;
import com.example.DisneyAppAlkemy.entity.Genders;
import com.example.DisneyAppAlkemy.services.GendersServices;
import com.example.DisneyAppAlkemy.services.MoviesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/genre")
@RestController
public class GenreController {

    @Autowired
    private GendersServices gendersServices;

    //funciona PostMan
    @PostMapping("/save")
    public ResponseEntity<Genders> save(@RequestParam("file") MultipartFile image,@Validated Genders genders){
        return new ResponseEntity<Genders>(gendersServices.save(genders), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Genders> getAllGenres() {
        return gendersServices.getAllGenders();
    }

}
