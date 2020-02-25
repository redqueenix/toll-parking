package com.assignment.exception;

/**
 * Exception thrown when the resource Slot is not found
 */
public class SlotNotFoundException extends TollParkingException{
    public SlotNotFoundException(String message) {
        super(message);
    }
}
