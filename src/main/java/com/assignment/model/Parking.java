package com.assignment.model;

import com.assignment.model.enums.Pricing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Model for the object Parking used also as DAO
 */
@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @JsonIgnore
    private Integer id;

    @NotNull
    @ApiModelProperty(example = "HOUR_SPENT_ONLY", required = true)
    @Enumerated(EnumType.STRING)
    private Pricing pricing;

    @NotNull
    @ApiModelProperty(example = "5", required = true)
    private int gasolineSlots;

    @NotNull
    @ApiModelProperty(example = "5", required = true)
    private int electric20Slots;

    @NotNull
    @ApiModelProperty(example = "0", required = true)
    private int electric50Slots;

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Double hourPrice;

    @ApiModelProperty(example = "5.0")
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

    public Double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Double hourPrice) {
        this.hourPrice = hourPrice;
    }

    public double getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Parking parking = (Parking) o;

        return new EqualsBuilder()
                .append(gasolineSlots, parking.gasolineSlots)
                .append(electric20Slots, parking.electric20Slots)
                .append(electric50Slots, parking.electric50Slots)
                .append(hourPrice, parking.hourPrice)
                .append(fixedAmount, parking.fixedAmount)
                .append(id, parking.id)
                .append(pricing, parking.pricing)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(pricing)
                .append(gasolineSlots)
                .append(electric20Slots)
                .append(electric50Slots)
                .append(hourPrice)
                .append(fixedAmount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("pricing", pricing)
                .append("gasolineSlots", gasolineSlots)
                .append("electric20Slots", electric20Slots)
                .append("electric50Slots", electric50Slots)
                .append("hourPrice", hourPrice)
                .append("fixedAmount", fixedAmount)
                .toString();
    }
}
