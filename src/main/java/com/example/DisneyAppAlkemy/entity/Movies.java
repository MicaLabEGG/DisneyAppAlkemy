package com.example.DisneyAppAlkemy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Movies {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer movieId;
    private String title;
    private String image;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date creationDate;
    private Integer rating;
    @ManyToMany(fetch=FetchType.LAZY,  mappedBy = "moviesId", cascade = CascadeType.ALL)
    private List<Characters> charactersID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="genderId")
    private Genders gendersId;
}
