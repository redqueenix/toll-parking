package com.assignment;

import com.assignment.exception.ConfigurationNotFoundException;
import com.assignment.exception.PricingNotFoundException;
import com.assignment.model.Parking;
import com.assignment.model.enums.Pricing;
import com.assignment.repository.ParkingRepository;
import com.assignment.service.PricingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Unit test for the service PricingService
 * @see PricingService
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PricingServiceTest {

    PricingService pricingService;
    private static Parking parking;
    private static final int PARKING_ID = 1;
    private static final int SLOT_E20 = 5;
    private static final int SLOT_E50 = 6;
    private static final int SLOT_GASO = 10;
    private static final int FIXED_AMOUNT = 5;
    private static final Double HOUR_PRICE = 2.5;
    private static final LocalDateTime START_HOUR = LocalDateTime.of(2020, Month.JANUARY, 1, 10, 10);
    private static final LocalDateTime END_HOUR = LocalDateTime.of(2020, Month.JANUARY, 1, 18, 40);

    @BeforeEach
    void init() {
        parking = new Parking();
        parking.setElectric20Slots(SLOT_E20);
        parking.setElectric50Slots(SLOT_E50);
        parking.setGasolineSlots(SLOT_GASO);
        parking.setHourPrice(HOUR_PRICE);
        parking.setId(PARKING_ID);
    }

    @Test
    public void whenCalculateWithHourOnly_thenReturnExactPrice(@Mock ParkingRepository parkingRepository) throws PricingNotFoundException, ConfigurationNotFoundException {
        // given
        parking.setPricing(Pricing.HOUR_SPENT_ONLY);
        when(parkingRepository.findById(PARKING_ID)).thenReturn(Optional.of(parking));
        pricingService = new PricingService(parkingRepository);
        // when
        double price = pricingService.calculatePrice(START_HOUR, END_HOUR);
        // then
        Assertions.assertEquals(hourDuration(START_HOUR, END_HOUR) * HOUR_PRICE, price);
    }

    @Test
    public void whenCalculateWithFixedAmount_thenReturnExactPrice(@Mock ParkingRepository parkingRepository) throws PricingNotFoundException, ConfigurationNotFoundException {
        // given
        parking.setPricing(Pricing.FIXED_AMOUNT_PLUS_HOUR_SPENT);
        parking.setFixedAmount(FIXED_AMOUNT);
        when(parkingRepository.findById(PARKING_ID)).thenReturn(Optional.of(parking));
        pricingService = new PricingService(parkingRepository);
        // when
        double price = pricingService.calculatePrice(START_HOUR, END_HOUR);
        // then
        Assertions.assertEquals(FIXED_AMOUNT + hourDuration(START_HOUR, END_HOUR) * HOUR_PRICE, price);
    }

    private long hourDuration(LocalDateTime startHour, LocalDateTime endHour) {
        Duration duration = Duration.between(startHour, endHour);
        return Math.abs(duration.toHours());
    }
}
