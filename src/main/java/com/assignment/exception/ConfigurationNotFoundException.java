package com.assignment.exception;
import com.assignment.model.Parking;

/**
 * Exception thrown when the resource {@link Parking} is not found
 */
public class ConfigurationNotFoundException extends TollParkingException{

    public ConfigurationNotFoundException(String message) {
        super(message);
    }
}
