package com.example.BookMyShow.models;

import com.example.BookMyShow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String movieName;

    private int duration;
    @OneToMany(mappedBy = "movie")
    private List<MovieShow> showList;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> requiredFeatures;
    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

}
