package com.springDataJPA.Assignment3.UserDefinedExceptions;

import org.springframework.http.HttpStatus;

public class DataPersistenceException extends UserDefinedProductExceptions{
    public DataPersistenceException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
