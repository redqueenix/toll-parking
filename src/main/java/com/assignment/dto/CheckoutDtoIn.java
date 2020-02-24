package com.assignment.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

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
