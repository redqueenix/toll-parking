package com.assignment.exception;

import com.assignment.model.Parking;

/**
 * Exception thrown when the resource {@link Parking} is not found
 */
public class PricingNotFoundException extends TollParkingException{
    public PricingNotFoundException(String message) {
        super(message);
    }
}
