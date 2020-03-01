package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

/**
 * Input DTO for the service /bill
 * @see TollParkingController#bill(BillDtoIn)
 */
public class BillDtoIn {

    @NotNull
    @ApiModelProperty(example = "13", required = true)
    private Long ticket;

    public Long getTicket() {
        return ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BillDtoIn billDtoIn = (BillDtoIn) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(ticket, billDtoIn.ticket)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
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