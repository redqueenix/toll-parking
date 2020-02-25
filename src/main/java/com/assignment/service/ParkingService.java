package com.assignment.service;

import com.assignment.dto.*;
import com.assignment.exception.*;
import com.assignment.model.Parking;
import com.assignment.model.Ticket;
import com.assignment.repository.ParkingRepository;
import com.assignment.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.assignment.model.enums.CarType.*;

/**
 * Service orchestrating the Parking activity of the Api
 */
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

    /**
     * Method to initiate Parking configuration
     *
     * @param initParkingDtoIn data needed to configure a parking
     */
    public void initialize(InitParkingDtoIn initParkingDtoIn) {
        Parking parking = initParkingDtoIn.getParking();
        parking.setId(1);
        parkingRepository.save(parking);
    }

    /**
     * Method to hundle the action of entering in the parking
     *
     * @param checkinDtoIn data needed to hundle the checkin of the customer's car in the parking
     * @return checkout information (ticket)
     */
    public CheckinDtoOut park(CheckinDtoIn checkinDtoIn) throws SlotNotFoundException, ConfigurationNotFoundException {
        CheckinDtoOut checkinDtoOut = new CheckinDtoOut();
        Parking parking = findParking(PARKING_ID);
        switch (checkinDtoIn.getCarType()) {
            case GASOLINE:
                if (parking.getGasolineSlots() > 0) {
                    parking.setGasolineSlots(parking.getGasolineSlots() - 1);
                    checkinDtoOut.setTicket(reserveSlot(parking, checkinDtoIn.getStartHour()));
                } else {
                    throw new SlotNotFoundException(String.format(SLOT_NOT_FOUND_MSG, GASOLINE.getDescription()));
                }
                break;
            case ELECTRIC_20:
                if (parking.getElectric20Slots() > 0) {
                    parking.setElectric20Slots(parking.getElectric20Slots() - 1);
                    checkinDtoOut.setTicket(reserveSlot(parking, checkinDtoIn.getStartHour()));
                } else {
                    throw new SlotNotFoundException(String.format(SLOT_NOT_FOUND_MSG, ELECTRIC_20.getDescription()));
                }
                break;
            case ELECTRIC_50:
                if (parking.getElectric50Slots() > 0) {
                    parking.setElectric50Slots(parking.getElectric50Slots() - 1);
                    checkinDtoOut.setTicket(reserveSlot(parking, checkinDtoIn.getStartHour()));
                } else {
                    throw new SlotNotFoundException(String.format(SLOT_NOT_FOUND_MSG, ELECTRIC_50.getDescription()));
                }
                break;
            default:
                throw new SlotNotFoundException(String.format("No slot available for the type %s", checkinDtoIn.getCarType().getDescription()));
        }
        return checkinDtoOut;
    }

    /**
     * Method to hundle when a customer car exit the parking
     *
     * @param checkoutDtoIn data needed to hundle the exit
     */
    public void exit(CheckoutDtoIn checkoutDtoIn) throws TicketNotFoundException, FunctionalException {
        Ticket ticket = findTicket(checkoutDtoIn.getTicket());
        if(ticket.getEndHour() != null) {
            throw new FunctionalException("Car already exit the parking ! :o");
        }
        ticket.setEndHour(checkoutDtoIn.getEndHour());
        ticketRepository.save(ticket);
    }

    /**
     * Method to hundle when a customer car exit the parking
     *
     * @param billDtoIn checkout data needed to bill the customer
     * @return the bill to return to the customer
     */
    public BillDtoOut bill(BillDtoIn billDtoIn) throws FunctionalException, PricingNotFoundException, ConfigurationNotFoundException, TicketNotFoundException {
        BillDtoOut billDtoOut = new BillDtoOut();
        Ticket ticket = findTicket(billDtoIn.getTicket());
        if (ticket.getEndHour() == null) {
            throw new FunctionalException("Please Checkout from parking before attempting to get your bill");
        }
        ticket.setPrice(pricingService.calculatePrice(ticket.getStartHour(), ticket.getEndHour()));
        billDtoOut.setTicket(ticket);
        ticketRepository.save(ticket);
        return billDtoOut;
    }

    private long reserveSlot(Parking parking, LocalDateTime startTime) {
        parkingRepository.save(parking);
        return ticketRepository.save(new Ticket(startTime)).getNumber();
    }

    private Ticket findTicket(long number) throws TicketNotFoundException {
        return ticketRepository.findById(number)
                .orElseThrow(() -> new TicketNotFoundException(String.format("No ticket found with the number %s", number)));
    }

    private Parking findParking(int id) throws ConfigurationNotFoundException {
        return parkingRepository.findById(id).orElseThrow(() -> new ConfigurationNotFoundException("No Parking Initialization Found"));
    }
}
