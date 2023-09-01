package com.example.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private Long dummyTicketId;
    private ResponseStatus status;
    private String message;
}
