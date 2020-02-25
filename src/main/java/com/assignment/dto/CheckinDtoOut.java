package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import io.swagger.annotations.ApiModelProperty;

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
}
