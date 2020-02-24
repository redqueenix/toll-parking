package com.assignment.dto;

import com.assignment.model.Ticket;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

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
