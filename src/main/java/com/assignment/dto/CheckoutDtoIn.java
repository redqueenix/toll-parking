package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Input DTO for the service /checkout
 * @see TollParkingController#checkout(CheckoutDtoIn)
 */
public class CheckoutDtoIn {

    @NotNull
    @ApiModelProperty(example = "13", required = true)
    private long ticket;

    @ApiModelProperty(example = "2020-10-03T15:15:30", required = true)
    private LocalDateTime endHour;

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalDateTime endHour) {
        this.endHour = endHour;
    }
}
