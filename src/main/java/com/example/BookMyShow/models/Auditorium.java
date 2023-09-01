package com.example.BookMyShow.models;
import com.example.BookMyShow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Auditorium extends BaseModel{
    private String number;
    private int maxRows;
    private int maxColumns;
    @ManyToOne
    private Theatre theatre;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> supportedFeatures;

    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seatsList;
    @OneToMany(mappedBy = "auditorium")
    private List<MovieShow> showsList;

}
