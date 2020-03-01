package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Input DTO for the service /checkout
 * @see TollParkingController#checkout(CheckoutDtoIn)
 */
public class CheckoutDtoIn {

    @NotNull
    @ApiModelProperty(example = "13", required = true)
    private Long ticket;

    @ApiModelProperty(example = "2020-10-03T15:15:30", required = true)
    private LocalDateTime endHour;

    public Long getTicket() {
        return ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalDateTime endHour) {
        this.endHour = endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CheckoutDtoIn that = (CheckoutDtoIn) o;

        return new EqualsBuilder()
                .append(ticket, that.ticket)
                .append(endHour, that.endHour)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(ticket)
                .append(endHour)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ticket", ticket)
                .append("endHour", endHour)
                .toString();
    }
}
