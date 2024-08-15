package com.springDataJPA.Assignment3.UserDefinedExceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserDefinedProductExceptions extends Exception{
    private final HttpStatus statusCode;
    public UserDefinedProductExceptions(String message, HttpStatus statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
