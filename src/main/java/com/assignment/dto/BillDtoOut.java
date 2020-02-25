package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.Ticket;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


/**
 * Output DTO for the service /bill
 * @see TollParkingController#bill(CheckoutDtoIn)
 */
public class BillDtoOut {

    @JsonUnwrapped
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
