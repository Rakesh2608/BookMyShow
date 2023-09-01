package com.example.BookMyShow.models;

import com.example.BookMyShow.models.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeatTypeInShow extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    @ManyToOne
    private MovieShow show;
    private double price;
}
