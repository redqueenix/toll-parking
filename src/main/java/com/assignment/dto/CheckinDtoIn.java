package com.assignment.dto;

import com.assignment.controller.TollParkingController;
import com.assignment.model.enums.CarType;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CheckinDtoIn that = (CheckinDtoIn) o;

        return new EqualsBuilder()
                .append(carType, that.carType)
                .append(startHour, that.startHour)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(carType)
                .append(startHour)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("carType", carType)
                .append("startHour", startHour)
                .toString();
    }
}
