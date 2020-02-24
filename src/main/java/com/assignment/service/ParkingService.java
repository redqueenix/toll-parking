package com.assignment.service;

import com.assignment.dto.*;
import com.assignment.exception.ConfigurationNotFoundException;
import com.assignment.exception.FunctionalException;
import com.assignment.exception.SlotNotFoundException;
import com.assignment.exception.TicketNotFoundException;
import com.assignment.model.Parking;
import com.assignment.model.Ticket;
import com.assignment.repository.ParkingRepository;
import com.assignment.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.assignment.model.enums.CarType.*;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PricingService pricingService;

    private static final int PARKING_ID = 1;
    private static final String SLOT_NOT_FOUND_MSG = "No slot left for the type %s";

    public void initialize(InitParkingDtoIn initParkingDtoIn) {
        Parking parking = initParkingDtoIn.getParking();
        parking.setId(1);
        parkingRepository.save(parking);
    }

    public CheckinDtoOut park(CheckinDtoIn checkinDtoIn) {
        CheckinDtoOut checkinDtoOut = new CheckinDtoOut();
        Parking parking = findParking(PARKING_ID);
        switch (checkinDtoIn.getCarType()) {
            case GASOLINE:
                if (parking.getGasolineSlots() > 0) {
                    parking.setGasolineSlots(parking.getGasolineSlots() - 1);
                    checkinDtoOut.setTicket(reserveSlot(parking));
                } else {
                    throw new SlotNotFoundException(String.format(SLOT_NOT_FOUND_MSG, GASOLINE.getDescription()));
                }
                break;
            case ELECTRIC_20:
                if (parking.getElectric20Slots() > 0) {
                    parking.setElectric20Slots(parking.getElectric20Slots() - 1);
                    checkinDtoOut.setTicket(reserveSlot(parking));
                } else {
                    throw new SlotNotFoundException(String.format(SLOT_NOT_FOUND_MSG, ELECTRIC_20.getDescription()));
                }
                break;
            case ELECTRIC_50:
                if (parking.getElectric50Slots() > 0) {
                    parking.setElectric50Slots(parking.getElectric50Slots() - 1);
                    checkinDtoOut.setTicket(reserveSlot(parking));
                } else {
                    throw new SlotNotFoundException(String.format(SLOT_NOT_FOUND_MSG, ELECTRIC_50.getDescription()));
                }
                break;
            default:
                throw new SlotNotFoundException(String.format("No slot available for the type %s", checkinDtoIn.getCarType().getDescription()));
        }
        return checkinDtoOut;
    }

    public void exit(CheckoutDtoIn checkoutDtoIn) {
        Ticket ticket = findTicket(checkoutDtoIn.getTicket());
        ticket.setEndHour(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public BillDtoOut bill(CheckoutDtoIn checkoutDtoIn) {
        BillDtoOut billDtoOut = new BillDtoOut();
        Ticket ticket = findTicket(checkoutDtoIn.getTicket());
        if (ticket.getEndHour() == null) {
            throw new FunctionalException("Please Checkout from parking before attempting to get your bill");
        }
        pricingService.calculatePrice(ticket.getStartHour(), ticket.getEndHour());

        return billDtoOut;
    }

    private long reserveSlot(Parking parking) {
        parkingRepository.save(parking);
        return ticketRepository.save(new Ticket(LocalDateTime.now())).getNumber();
    }

    private Ticket findTicket(long number) {
        return ticketRepository.findById(number)
                .orElseThrow(() -> new TicketNotFoundException(String.format("No ticket found with the number %s", number)));
    }

    private Parking findParking(int id) {
        return parkingRepository.findById(1).orElseThrow(() -> new ConfigurationNotFoundException("No Parking Initialization Found"));
    }
}
