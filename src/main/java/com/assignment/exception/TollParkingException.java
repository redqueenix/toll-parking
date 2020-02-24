package com.assignment.exception;

public class TollParkingException extends RuntimeException{

    public TollParkingException(String message) {
        super(message);
    }

    public TollParkingException(String message, Throwable cause) {
        super(message, cause);
    }
}
