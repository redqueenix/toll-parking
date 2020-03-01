package com.assignment.model;

import com.assignment.model.enums.CarType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Model for the object Ticket used also as DAO
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(example = "13")
    @JsonProperty("ticket")
    private long number;

    @ApiModelProperty(example = "2020-10-03T10:15:30")
    private LocalDateTime startHour;

    @ApiModelProperty(example = "2020-10-03T15:15:30")
    private LocalDateTime endHour;

    @ApiModelProperty(example = "GASOLINE")
    private CarType carType;

    @ApiModelProperty(example = "5.0")
    private Double price;

    public Ticket() {
    }

    public Ticket(LocalDateTime startHour, CarType carType) {
        this.startHour = startHour;
        this.carType = carType;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public LocalDateTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalDateTime startHour) {
        this.startHour = startHour;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalDateTime endHour) {
        this.endHour = endHour;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("startHour", startHour)
                .append("endHour", endHour)
                .append("carType", carType)
                .append("price", price)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return new EqualsBuilder()
                .append(number, ticket.number)
                .append(startHour, ticket.startHour)
                .append(endHour, ticket.endHour)
                .append(carType, ticket.carType)
                .append(price, ticket.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(number)
                .append(startHour)
                .append(endHour)
                .append(carType)
                .append(price)
                .toHashCode();
    }
}
