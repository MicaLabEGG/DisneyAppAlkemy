package com.example.DisneyAppAlkemy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Characters {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer characterId;
    private String name;
    private String image;
    private Integer age;
    private Integer weight;
    private String history;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "characters_movies",
            joinColumns = {
                    @JoinColumn(name = "characterId", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "movieId", nullable = false)})
    private List<Movies> moviesId;
}
