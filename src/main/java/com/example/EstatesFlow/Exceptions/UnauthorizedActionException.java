package com.example.EstatesFlow.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message){
        super(message);
    }
}
