package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.enums.CarType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Input DTO for the service /checkIn
 * @see TollParkingController#checkIn(CheckinDtoIn)
 */
public class CheckinDtoIn {

    @NotNull
    @ApiModelProperty(example = "GASOLINE", required = true)
    private CarType carType;

    @NotNull
    @ApiModelProperty(example = "2020-10-03T10:15:30", required = true)
    private LocalDateTime startHour;

    public LocalDateTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalDateTime startHour) {
        this.startHour = startHour;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
