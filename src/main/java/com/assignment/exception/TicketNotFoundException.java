package com.assignment.exception;

import com.assignment.model.Ticket;

/**
 * Exception thrown when the resource {@link Ticket} is not found
 */
public class TicketNotFoundException extends TollParkingException{
    public TicketNotFoundException(String message) {
        super(message);
    }
}
