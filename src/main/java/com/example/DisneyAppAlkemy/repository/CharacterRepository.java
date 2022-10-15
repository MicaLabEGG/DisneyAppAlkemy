package com.example.DisneyAppAlkemy.repository;

import com.example.DisneyAppAlkemy.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Characters, Integer> {

    @Query("SELECT c FROM Characters c WHERE c.name = :name")
    public List<Characters> findByName(@Param("name")String name);

    @Query("SELECT c FROM Characters c WHERE c.age = :age")
    public List<Characters> findByAge(@Param("age")Integer age);

    @Query("SELECT c FROM Characters c JOIN c.moviesId m WHERE m.movieId = :movieId")
    public List<Characters> findByMoviesID(@Param("movieId")Integer movieId);
}
