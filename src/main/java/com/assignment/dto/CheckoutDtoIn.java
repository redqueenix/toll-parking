package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Input DTO for the service /checkout
 * @see TollParkingController#checkout(CheckoutDtoIn)
 */
public class CheckoutDtoIn {

    @NotNull
    @ApiModelProperty(example = "13", required = true)
    private long ticket;

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }
}
