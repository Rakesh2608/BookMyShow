package com.example.BookMyShow.models;

import com.example.BookMyShow.models.enums.Feature;
import com.example.BookMyShow.models.enums.ShowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieShow extends BaseModel{
    @ManyToOne
    private Auditorium auditorium;
    @ManyToOne
    private Movie movie;

    private Date startTime;
    private Date endTime;
    @Enumerated(EnumType.ORDINAL)
    private ShowStatus showStatus;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> requiredFeatures;
    private int maxTicket;
    private int lockedTimeout;
    @OneToMany(mappedBy = "show")
    private List<SeatInShow> seatInShows;
    @OneToMany(mappedBy = "show")
    private List<SeatTypeInShow> seatTypeInShows;

}
