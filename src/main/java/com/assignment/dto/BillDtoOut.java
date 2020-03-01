package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.Ticket;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Output DTO for the service /bill
 * @see TollParkingController#bill(BillDtoIn)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BillDtoOut that = (BillDtoOut) o;

        return new EqualsBuilder()
                .append(ticket, that.ticket)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(ticket)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ticket", ticket)
                .toString();
    }
}
