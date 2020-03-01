package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.Parking;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        InitParkingDtoIn that = (InitParkingDtoIn) o;

        return new EqualsBuilder()
                .append(parking, that.parking)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(parking)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("parking", parking)
                .toString();
    }
}
