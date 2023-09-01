package com.example.BookMyShow.exceptions;

public class UserNotFoundException extends  Exception{

    public UserNotFoundException(){
        super("User not found");
    }
}
