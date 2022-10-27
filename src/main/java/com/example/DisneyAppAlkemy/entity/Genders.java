package com.example.DisneyAppAlkemy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Genders {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer genreId;
    private String name;
    private String image;
    @OneToMany(mappedBy = "gendersId",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movies> moviesId;
}
