package com.assignment.dto;

import io.swagger.annotations.ApiModelProperty;

public class CheckinDtoOut {

    @ApiModelProperty(example = "13")
    private long ticket;

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }
}
