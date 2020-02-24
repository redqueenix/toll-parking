package com.assignment.dto;

import com.assignment.model.Parking;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.validation.Valid;

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
