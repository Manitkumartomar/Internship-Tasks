package com.userservice.bank.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(){
        super("Resources not found!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
