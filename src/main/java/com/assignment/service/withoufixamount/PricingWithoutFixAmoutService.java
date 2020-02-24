package com.assignment.service.withoufixamount;

import com.assignment.service.PricingService;

import java.time.LocalDateTime;

public class PricingWithoutFixAmoutService extends PricingService {

    public double calculatePrice(double fixedAmount, double hourPrice, LocalDateTime startHour, LocalDateTime endHour) {
        return 0;
    }

}
