package com.assignment.controller;

import com.assignment.exception.*;
import com.assignment.model.Error;
import com.assignment.model.enums.CodeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception hundler for the controller {@link TollParkingController}
 */
@ControllerAdvice
public class TollParkingHundler {

    /**
     * hundle exception of type {@link ConfigurationNotFoundException}
     * @param e thrown exception
     * @return formated error
     */
    @ExceptionHandler(ConfigurationNotFoundException.class)
    public ResponseEntity<Error> handleConfigurationNotFoundException(ConfigurationNotFoundException e) {
        return new ResponseEntity<>(new Error(CodeError.CONFIG_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * hundle exception of type {@link SlotNotFoundException}
     * @param e thrown exception
     * @return formated error
     */
    @ExceptionHandler(SlotNotFoundException.class)
    public ResponseEntity<Error> handleFSlotNotFoundException(SlotNotFoundException e) {
        return new ResponseEntity<>(new Error(CodeError.SLOT_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * hundle exception of type {@link TicketNotFoundException}
     * @param e thrown exception
     * @return formated error
     */
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Error> handleTicketNotFoundException(TicketNotFoundException e) {
        return new ResponseEntity<>(new Error(CodeError.TICKET_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * hundle exception of type {@link PricingNotFoundException}
     * @param e thrown exception
     * @return formated error
     */
    @ExceptionHandler(PricingNotFoundException.class)
    public ResponseEntity<Error> handlePricingNotFoundException(PricingNotFoundException e) {
        return new ResponseEntity<>(new Error(CodeError.PRICING_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * hundle exception of type {@link FunctionalException}
     * @param e thrown exception
     * @return formated error
     */
    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity<Error> handleFunctionalException(FunctionalException e) {
        return new ResponseEntity<>(new Error(CodeError.FUNCT_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
