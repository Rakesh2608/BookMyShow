package com.example.BookMyShow.models;

import com.example.BookMyShow.models.enums.SeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class SeatInShow extends BaseModel{
    private Date lockedAt;
    @ManyToOne
    private Seat seat;
    @ManyToOne
    private MovieShow show;
    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;
}
