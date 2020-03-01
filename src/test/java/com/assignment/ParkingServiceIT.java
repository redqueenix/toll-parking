package com.assignment;

import com.assignment.dto.*;
import com.assignment.exception.*;
import com.assignment.model.Parking;
import com.assignment.model.Ticket;
import com.assignment.model.enums.CarType;
import com.assignment.model.enums.Pricing;
import com.assignment.repository.ParkingRepository;
import com.assignment.repository.TicketRepository;
import com.assignment.service.ParkingService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Integration test for the service ParkingService
 *
 * @see ParkingService
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParkingServiceIT {

    @Autowired
    ParkingService parkingService;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    TicketRepository ticketRepository;

    private static long ticketNumber = 0;
    private static final int PARKING_ID = 1;
    private static final int SLOT_E20 = 5;
    private static final int SLOT_E50 = 6;
    private static final int SLOT_GASO = 10;
    private static final Double HOUR_PRICE = 2.5;
    private static final LocalDateTime START_HOUR = LocalDateTime.of(2020, Month.JANUARY, 1, 16, 20);
    private static final LocalDateTime END_HOUR = LocalDateTime.of(2020, Month.JANUARY, 1, 20, 20);
    private static final LocalDateTime WRONG_END_HOUR = LocalDateTime.of(2020, Month.JANUARY, 1, 10, 20);

    @Test
    @Order(1)
    public void whenParkWithoutParkingConfiguration_ThenThrowConfigurationNotFoundException() {
        // given
        CheckinDtoIn checkinDtoIn = new CheckinDtoIn();
        checkinDtoIn.setCarType(CarType.ELECTRIC_20);
        checkinDtoIn.setStartHour(START_HOUR);
        // when then
        Assertions.assertThrows(ConfigurationNotFoundException.class, () ->
                parkingService.park(checkinDtoIn)
        );

    }

    @Test
    @Order(2)
    public void whenInitialize_thenfindInitializedParking() {
        // given
        InitParkingDtoIn initParkingDtoIn = new InitParkingDtoIn();
        Parking parking = new Parking();
        parking.setElectric20Slots(SLOT_E20);
        parking.setElectric50Slots(SLOT_E50);
        parking.setGasolineSlots(SLOT_GASO);
        parking.setPricing(Pricing.HOUR_SPENT_ONLY);
        parking.setHourPrice(HOUR_PRICE);
        parking.setId(PARKING_ID);
        initParkingDtoIn.setParking(parking);
        // when
        parkingService.initialize(initParkingDtoIn);
        //then
        Parking parkingPersisted = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parkingPersisted);
        Assertions.assertEquals(SLOT_E20, parkingPersisted.getElectric20Slots());
        Assertions.assertEquals(SLOT_E50, parkingPersisted.getElectric50Slots());
        Assertions.assertEquals(SLOT_GASO, parkingPersisted.getGasolineSlots());
        Assertions.assertEquals(Pricing.HOUR_SPENT_ONLY, parkingPersisted.getPricing());
    }

    @Test
    @Order(3)
    public void whenExitWithWrongTicket_ThenThrowTicketNotFoundException() {
        // given
        CheckoutDtoIn checkinDtoIn = new CheckoutDtoIn();
        checkinDtoIn.setTicket(ticketNumber);
        checkinDtoIn.setEndHour(END_HOUR);
        // when then
        Assertions.assertThrows(TicketNotFoundException.class, () ->
                parkingService.exit(checkinDtoIn)
        );
    }

    @Test
    @Order(4)
    public void whenPark_ThenCheckSLotInformationAndTicket() throws SlotNotFoundException, ConfigurationNotFoundException {
        // given
        CheckinDtoIn checkinDtoIn = new CheckinDtoIn();
        checkinDtoIn.setCarType(CarType.ELECTRIC_20);
        checkinDtoIn.setStartHour(START_HOUR);
        // when
        CheckinDtoOut checkinDtoOut = parkingService.park(checkinDtoIn);
        //then
        Ticket ticket = ticketRepository.findById(checkinDtoOut.getTicket()).get();
        Assertions.assertNotNull(ticket);
        ticketNumber = ticket.getNumber(); // ticketNumber to use for following tests
        Assertions.assertEquals(START_HOUR, ticket.getStartHour());
        Assertions.assertEquals(CarType.ELECTRIC_20, ticket.getCarType());
        Parking parking = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parking);
        Assertions.assertEquals(SLOT_E20 - 1, parking.getElectric20Slots());
    }

    @Test
    @Order(5)
    public void whenExitWithWrongEndHour_ThenThrowFunctionalException() {
        // given
        CheckoutDtoIn checkinDtoIn = new CheckoutDtoIn();
        checkinDtoIn.setTicket(ticketNumber);
        checkinDtoIn.setEndHour(WRONG_END_HOUR);
        // when then
        Assertions.assertThrows(FunctionalException.class, () ->
                parkingService.exit(checkinDtoIn)
        );
    }

    @Test
    @Order(6)
    public void whenBillWithoutCheckout_ThenThrowFunctionalException() {
        // given
        BillDtoIn billDtoIn = new BillDtoIn();
        billDtoIn.setTicket(ticketNumber);
        // when then
        Assertions.assertThrows(FunctionalException.class, () ->
                parkingService.bill(billDtoIn)
        );
    }

    @Test
    @Order(7)
    public void whenExitOK_ThenCheckTicketInformationAndParkingSlot() throws ConfigurationNotFoundException, TicketNotFoundException, FunctionalException {
        // given
        CheckoutDtoIn checkinDtoIn = new CheckoutDtoIn();
        checkinDtoIn.setTicket(ticketNumber);
        checkinDtoIn.setEndHour(END_HOUR);
        // when
        parkingService.exit(checkinDtoIn);
        // then
        Ticket ticket = ticketRepository.findById(ticketNumber).get();
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(END_HOUR, ticket.getEndHour());
        Parking parking = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parking);
        Assertions.assertEquals(SLOT_E20, parking.getElectric20Slots());
    }

    @Test
    @Order(8)
    public void whenExitWithOldTicket_ThenThrowFunctionalException() {
        // given
        CheckoutDtoIn checkinDtoIn = new CheckoutDtoIn();
        checkinDtoIn.setTicket(ticketNumber);
        checkinDtoIn.setEndHour(END_HOUR);
        // when then
        Assertions.assertThrows(FunctionalException.class, () ->
                parkingService.exit(checkinDtoIn)
        );
    }

    @Test
    @Order(9)
    public void whenBillOK_ThenTCheckBillInformation() throws TicketNotFoundException, PricingNotFoundException, ConfigurationNotFoundException, FunctionalException {
        // given
        BillDtoIn billDtoIn = new BillDtoIn();
        billDtoIn.setTicket(ticketNumber);
        // when
        BillDtoOut billDtoOut = parkingService.bill(billDtoIn);
        // then
        Assertions.assertNotNull(billDtoOut);
        Assertions.assertNotNull(billDtoOut.getTicket());
        // compare with stored informations from previous tests
        Assertions.assertEquals(ticketNumber, billDtoOut.getTicket().getNumber());
        Assertions.assertEquals(CarType.ELECTRIC_20, billDtoOut.getTicket().getCarType());
        Assertions.assertEquals(START_HOUR, billDtoOut.getTicket().getStartHour());
        Assertions.assertEquals(END_HOUR, billDtoOut.getTicket().getEndHour());
        Assertions.assertNotNull(billDtoOut.getTicket().getPrice()); // price value tested in an external test PricingServiceTest
    }

    @Test
    @Order(10)
    public void whenParkE50_ThenCheckSLotInformationAndTicket() throws SlotNotFoundException, ConfigurationNotFoundException {
        // given
        CheckinDtoIn checkinDtoIn = new CheckinDtoIn();
        checkinDtoIn.setCarType(CarType.ELECTRIC_50);
        checkinDtoIn.setStartHour(START_HOUR);
        // when
        CheckinDtoOut checkinDtoOut = parkingService.park(checkinDtoIn);
        //then
        Ticket ticket = ticketRepository.findById(checkinDtoOut.getTicket()).get();
        Assertions.assertNotNull(ticket);
        ticketNumber = ticket.getNumber(); // ticketNumber to use for following tests
        Assertions.assertEquals(START_HOUR, ticket.getStartHour());
        Assertions.assertEquals(CarType.ELECTRIC_50, ticket.getCarType());
        Parking parking = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parking);
        Assertions.assertEquals(SLOT_E50 - 1, parking.getElectric50Slots());
    }

    @Test
    @Order(11)
    public void whenExitE50OK_ThenCheckTicketInformationAndParkingSlot() throws ConfigurationNotFoundException, TicketNotFoundException, FunctionalException {
        // given
        CheckoutDtoIn checkinDtoIn = new CheckoutDtoIn();
        checkinDtoIn.setTicket(ticketNumber);
        checkinDtoIn.setEndHour(END_HOUR);
        // when
        parkingService.exit(checkinDtoIn);
        // then
        Ticket ticket = ticketRepository.findById(ticketNumber).get();
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(END_HOUR, ticket.getEndHour());
        Parking parking = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parking);
        Assertions.assertEquals(SLOT_E50, parking.getElectric50Slots());
    }

    @Test
    @Order(12)
    public void whenParkGASO_ThenCheckSLotInformationAndTicket() throws SlotNotFoundException, ConfigurationNotFoundException {
        // given
        CheckinDtoIn checkinDtoIn = new CheckinDtoIn();
        checkinDtoIn.setCarType(CarType.GASOLINE);
        checkinDtoIn.setStartHour(START_HOUR);
        // when
        CheckinDtoOut checkinDtoOut = parkingService.park(checkinDtoIn);
        //then
        Ticket ticket = ticketRepository.findById(checkinDtoOut.getTicket()).get();
        Assertions.assertNotNull(ticket);
        ticketNumber = ticket.getNumber(); // ticketNumber to use for following tests
        Assertions.assertEquals(START_HOUR, ticket.getStartHour());
        Assertions.assertEquals(CarType.GASOLINE, ticket.getCarType());
        Parking parking = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parking);
        Assertions.assertEquals(SLOT_GASO - 1, parking.getGasolineSlots());
    }

    @Test
    @Order(13)
    public void whenExitGASOOK_ThenCheckTicketInformationAndParkingSlot() throws ConfigurationNotFoundException, TicketNotFoundException, FunctionalException {
        // given
        CheckoutDtoIn checkinDtoIn = new CheckoutDtoIn();
        checkinDtoIn.setTicket(ticketNumber);
        checkinDtoIn.setEndHour(END_HOUR);
        // when
        parkingService.exit(checkinDtoIn);
        // then
        Ticket ticket = ticketRepository.findById(ticketNumber).get();
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(END_HOUR, ticket.getEndHour());
        Parking parking = parkingRepository.findById(PARKING_ID).get();
        Assertions.assertNotNull(parking);
        Assertions.assertEquals(SLOT_GASO, parking.getGasolineSlots());
    }


}
