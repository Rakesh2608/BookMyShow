package com.example.BookMyShow.models;

import com.example.BookMyShow.models.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Actor extends BaseModel{
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @ManyToMany
    private List<Movie> movies;

}
