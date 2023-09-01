package com.example.BookMyShow.services;

import com.example.BookMyShow.exceptions.SeatNotAvailalbleException;
import com.example.BookMyShow.exceptions.UserNotFoundException;
import com.example.BookMyShow.models.SeatInShow;
import com.example.BookMyShow.models.Ticket;
import com.example.BookMyShow.models.User;
import com.example.BookMyShow.models.enums.SeatStatus;
import com.example.BookMyShow.repositories.SeatInShowRepository;
import com.example.BookMyShow.repositories.TicketRepository;
import com.example.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private UserRepository userRepository;
    private TicketRepository ticketRepository;
    private SeatInShowRepository seatInShowRepository;
    @Autowired
    TicketService(UserRepository userRepository,TicketRepository ticketRepository,SeatInShowRepository seatInShowRepository){
        this.userRepository=userRepository;
        this.ticketRepository=ticketRepository;
        this.seatInShowRepository=seatInShowRepository;
    }

    // 1. Fetch seatInShows
    // 2. Check if seats are available
    // 3. Fetch user
    // 4.1 Yes => Lock seats, make dummy ticket and return
    // 4.2 No => Throw and exception
    public Ticket bookTicketService(Long userId, List<Long> seatInShowIds) throws SeatNotAvailalbleException, UserNotFoundException {
        Ticket ticket=new Ticket();
        List<SeatInShow> seats=seatInShowRepository.findAllById(seatInShowIds);
        for(SeatInShow seat:seats){
            if(isAvailable(seat)==false){
                throw new SeatNotAvailalbleException();
            }
        }

        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        User bookedBy=user.get();

        List<SeatInShow> updatedSeats=new ArrayList<>();
        Date current=new Date();
        for(SeatInShow seat:seats){
            seat.setSeatStatus(SeatStatus.LOCKED);
            seat.setLockedAt(current);
            seatInShowRepository.save(seat);
            updatedSeats.add(seat);
        }

        ticket.setBookedBy(bookedBy);
        ticket.setAmount(1000);
        ticket.setSeatInShows(updatedSeats);
        ticket.setBookedAt(current);
        Ticket bookedTicket=ticketRepository.save(ticket);

        return bookedTicket;

    }
    public boolean isAvailable(SeatInShow seat){
        if(seat.getSeatStatus().equals(SeatStatus.AVAILABLE)){
            return true;
        }
        else if(seat.getSeatStatus().equals(SeatStatus.LOCKED)){
            //logic
            long lockedAt=seat.getLockedAt().getTime();
            long currentTime=System.currentTimeMillis();
            long duration=currentTime-lockedAt;
            long durationInSeconds=duration/1000;
            long durationInMinutes=durationInSeconds/60;

            if(durationInMinutes>10){
                return true;
            }
            else{
                return false;
            }

        }
        return false;
    }
}
