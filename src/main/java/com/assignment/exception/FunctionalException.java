package com.assignment.exception;


/**
 * Exception thrown when there's a functional error
 */
public class FunctionalException extends TollParkingException{
    public FunctionalException(String message) {
        super(message);
    }
}
