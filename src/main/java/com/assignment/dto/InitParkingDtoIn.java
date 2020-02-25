package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.Parking;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.validation.Valid;

/**
 * Input DTO for the service /initParking
 * @see TollParkingController#initParking(InitParkingDtoIn)
 */
public class InitParkingDtoIn {

    @Valid
    @JsonUnwrapped
    private Parking parking;

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
