package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Output DTO for the service /checkIn
 * @see TollParkingController#checkIn(CheckinDtoIn)
 */
public class CheckinDtoOut {

    @ApiModelProperty(example = "13")
    private long ticket;

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CheckinDtoOut that = (CheckinDtoOut) o;

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
