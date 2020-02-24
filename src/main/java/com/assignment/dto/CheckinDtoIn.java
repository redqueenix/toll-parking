package com.assignment.dto;

import com.assignment.model.enums.CarType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

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
