package com.example.BookMyShow.repositories;

import com.example.BookMyShow.models.SeatInShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatInShowRepository extends JpaRepository<SeatInShow,Long> {
}
