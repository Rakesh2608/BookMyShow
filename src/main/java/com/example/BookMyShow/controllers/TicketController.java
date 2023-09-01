package com.example.BookMyShow.controllers;

import com.example.BookMyShow.dtos.BookTicketRequestDto;
import com.example.BookMyShow.dtos.BookTicketResponseDto;
import com.example.BookMyShow.dtos.ResponseStatus;
import com.example.BookMyShow.models.Ticket;
import com.example.BookMyShow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    // generate a ticket => select the seats
    // 1. retrieve
    // 2. check if available
    // 3.1 no => exception
    // 3.2 yes
    // lock the seats
    // generate a dummy ticket
    // redirect to payment gateway
    private TicketService ticketService;
    @Autowired
    TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    BookTicketResponseDto bookTicket(BookTicketRequestDto bookTicketRequestDto){
        BookTicketResponseDto bookTicketResponseDto=new BookTicketResponseDto();
        try{
            Ticket ticket=ticketService.bookTicketService(bookTicketRequestDto.getUserId(),bookTicketRequestDto.getSeatInShowIds());
            bookTicketResponseDto.setStatus(ResponseStatus.SUCCESS);
            bookTicketResponseDto.setDummyTicketId(ticket.getId());
            bookTicketResponseDto.setMessage("Ticket Booked Successfully");

        }
        catch (Exception ex){
            bookTicketResponseDto.setMessage("Something went Wrong!!!!");
            bookTicketResponseDto.setStatus(ResponseStatus.FAIURE);
        }

        return bookTicketResponseDto;
    }
}


// 1 select seats (bookTicket) => seats are locked, dummy ticket generated
// 2 sent to payment gateway with dummy ticket id => does payment and gets a (transactionId). redirected to bms
// 3 confirmTicket (ticketId, transactionId) => seats are booked, put payment details in dummy ticket (real ticket)