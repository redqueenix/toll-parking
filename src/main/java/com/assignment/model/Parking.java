package com.assignment.model;

import com.assignment.model.enums.Pricing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @NotNull
    @ApiModelProperty(example = "HOUR_SPENT_ONLY", required = true)
    @Enumerated(EnumType.STRING)
    private Pricing pricing;

    @NotNull
    @ApiModelProperty(example = "0", required = true)
    private int gasolineSlots;

    @NotNull
    @ApiModelProperty(example = "0", required = true)
    private int electric20Slots;

    @NotNull
    @ApiModelProperty(example = "0", required = true)
    private int electric50Slots;

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private double hourPrice;

    @NotNull
    @ApiModelProperty(example = "5.0", required = true)
    private double fixedAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public int getGasolineSlots() {
        return gasolineSlots;
    }

    public void setGasolineSlots(int gasolineSlots) {
        this.gasolineSlots = gasolineSlots;
    }

    public int getElectric20Slots() {
        return electric20Slots;
    }

    public void setElectric20Slots(int electric20Slots) {
        this.electric20Slots = electric20Slots;
    }

    public int getElectric50Slots() {
        return electric50Slots;
    }

    public void setElectric50Slots(int electric50Slots) {
        this.electric50Slots = electric50Slots;
    }

    public double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }

    public double getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }
}
