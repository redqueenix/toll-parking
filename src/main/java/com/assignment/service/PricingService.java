package com.assignment.service;

import com.assignment.exception.ConfigurationNotFoundException;
import com.assignment.exception.PricingNotFoundException;
import com.assignment.model.Parking;
import com.assignment.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Service to manage the pricing of the Poll Parking
 */
@Service
public class PricingService {

    private final ParkingRepository parkingRepository;

    private static final int PARKING_ID = 1;

    public PricingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    /**
     * Method to orchestrate the different pricing method
     * @param startHour when the car entered the parking
     * @param endHour when the car left the parking
     * @return the billing price
     */
    public double calculatePrice(LocalDateTime startHour, LocalDateTime endHour) throws ConfigurationNotFoundException, PricingNotFoundException {
        Parking parking = parkingRepository.findById(PARKING_ID).orElseThrow(() -> new ConfigurationNotFoundException("No Parking Initialization Found"));
        double hourPrice = parking.getHourPrice();
        double fixedAmount = parking.getFixedAmount();
        switch (parking.getPricing()) {
            case HOUR_SPENT_ONLY:
                return calculatePrice(hourPrice, startHour, endHour);
            case FIXED_AMOUNT_PLUS_HOUR_SPENT:
                return calculatePrice(fixedAmount, hourPrice, startHour, endHour);
            default:
                throw new PricingNotFoundException(String.format("Pricing %s not available", parking.getPricing()));
        }
    }

    private double calculatePrice(double hourPrice, LocalDateTime startHour, LocalDateTime endHour){
        return hourPrice * hourDuration(startHour, endHour);
    }

    private double calculatePrice(double fixedAmount, double hourPrice, LocalDateTime startHour, LocalDateTime endHour){
        return fixedAmount + hourPrice * hourDuration(startHour, endHour);
    }

    private long hourDuration(LocalDateTime startHour, LocalDateTime endHour){
        Duration duration = Duration.between(startHour, endHour);
        return Math.abs(duration.toHours());
    }

}
