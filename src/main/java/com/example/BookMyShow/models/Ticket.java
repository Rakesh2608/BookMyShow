package com.example.BookMyShow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    private double amount;
    private User bookedBy;
    private Date bookedAt;
    private List<SeatInShow> seatInShows;
    private Payment payment;
}
