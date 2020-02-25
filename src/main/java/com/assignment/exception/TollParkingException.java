package com.assignment.exception;

/**
 * Global exception for the application TollParking
 */
class TollParkingException extends Exception{

    TollParkingException(String message) {
        super(message);
    }

    TollParkingException(String message, Throwable cause) {
        super(message, cause);
    }
}
