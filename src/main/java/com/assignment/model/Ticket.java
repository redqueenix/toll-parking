package com.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ApiModelProperty(example = "5.0")
    private Double price;

    public Ticket() {
    }

    public Ticket(LocalDateTime startHour) {
        this.startHour = startHour;
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
}
