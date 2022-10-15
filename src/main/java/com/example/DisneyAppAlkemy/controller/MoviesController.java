package com.example.DisneyAppAlkemy.controller;

import com.example.DisneyAppAlkemy.entity.Characters;
import com.example.DisneyAppAlkemy.entity.Movies;
import com.example.DisneyAppAlkemy.services.GendersServices;
import com.example.DisneyAppAlkemy.services.MoviesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MoviesController {

    @Autowired
    private MoviesServices moviesServices;
    @Autowired
    private GendersServices gendersServices;

    @PostMapping("save")
    public Movies save(@RequestParam("file") MultipartFile image, @ModelAttribute Movies movies) {
        if (!image.isEmpty()) {
            Path imagesPath = Paths.get("src//main//resources//static//images");
            String absolutPath = imagesPath.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path route = Paths.get(absolutPath + image.getOriginalFilename());
                Files.write(route, bytes);
                movies.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return moviesServices.save(movies);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<Movies> updateCMovies(@RequestBody Movies movies){
        if(movies.getMovieId() == null){
            return ResponseEntity.badRequest().build();
        }
        Movies result = moviesServices.save(movies);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public List<Movies> getAllMovies() {

        return moviesServices.findAll();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movies> findById(@PathVariable("movieId") Integer movieId) {
        Optional<Movies> moviopt = moviesServices.findById(movieId);
        if(moviopt.isPresent()){
            return ResponseEntity.ok(moviopt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(params="name")
    public List<Movies> findByTitle(@RequestParam("name") String title){
        return moviesServices.findByTitle(title);
    }

    @GetMapping(value = "", params="genreId")
    public List<Movies> getByGenre(@RequestParam("genreId") Integer genreId){
        return gendersServices.getMoviesByGenreId(genreId);
    }

    @GetMapping(params="order")
    public List<Movies> getByOrder(@RequestParam("order") String order){
        return moviesServices.getByOrder(order);
    }

    @DeleteMapping(path = "delete/{movieId}")
    public String delete(@PathVariable("movieId") Integer movieId){
        try {
            moviesServices.delete(movieId);
            return "Movie was deleted id: " + movieId;
        } catch (Exception e) {
            return "Movie cannot deleted id: " + movieId;
        }
    }
}
