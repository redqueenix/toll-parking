package com.assignment.service;

import com.assignment.exception.ConfigurationNotFoundException;
import com.assignment.exception.PricingNotFoundException;
import com.assignment.model.Parking;
import com.assignment.repository.ParkingRepository;
import com.assignment.service.fixamount.PricingFixAmoutService;
import com.assignment.service.withoufixamount.PricingWithoutFixAmoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public abstract class PricingService {

    @Autowired
    private PricingFixAmoutService pricingFixAmoutService;
    @Autowired
    private PricingWithoutFixAmoutService pricingWithoutFixAmoutService;

    @Autowired
    private ParkingRepository parkingRepository;

    private static final int PARKING_ID = 1;

    double calculatePrice(LocalDateTime startHour, LocalDateTime endHour) {
        Parking parking = parkingRepository.findById(PARKING_ID).orElseThrow(() -> new ConfigurationNotFoundException("No Parking Initialization Found"));
        double hourPrice = parking.getHourPrice();
        double fixedAmount = parking.getFixedAmount();
        switch (parking.getPricing()) {
            case HOUR_SPENT_ONLY:
                return pricingFixAmoutService.calculatePrice(hourPrice, startHour, endHour);
            case FIXED_AMOUNT_PLUS_HOUR_SPENT:
                return pricingWithoutFixAmoutService.calculatePrice(fixedAmount, hourPrice, startHour, endHour);
            default:
                throw new PricingNotFoundException(String.format("Pricing %s not available", parking.getPricing()));
        }
    }
}
