package com.example.DisneyAppAlkemy.repository;

import com.example.DisneyAppAlkemy.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    @Query("SELECT m FROM Movies m WHERE m.title = :title")
    public List<Movies> findByTitle(@Param("title")String title);

    @Query("SELECT m FROM Movies m")
    public List<Movies> getAll();

    @Query(value = "SELECT title,image,creationDate FROM movies ORDER BY creation_date ASC",nativeQuery = true)
    public List<Movies> getAllByOrderASC();

    @Query(value = "SELECT title,image,creation_date FROM movies ORDER BY creation_date DESC",nativeQuery = true)
    public List<Movies> getAllByOrderDESC();

    public Movies getById(Integer movieId);

}
