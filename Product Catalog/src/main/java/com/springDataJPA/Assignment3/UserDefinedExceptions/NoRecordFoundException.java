package com.springDataJPA.Assignment3.UserDefinedExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class NoRecordFoundException extends UserDefinedProductExceptions{
    public NoRecordFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
