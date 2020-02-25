package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.enums.CarType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Input DTO for the service /checkIn
 * @see TollParkingController#checkIn(CheckinDtoIn)
 */
public class CheckinDtoIn {

    @NotNull
    @ApiModelProperty(example = "GASOLINE", required = true)
    private CarType carType;

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
