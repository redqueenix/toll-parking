package com.assignment.controller;

import com.assignment.dto.*;
import com.assignment.exception.*;
import com.assignment.service.ParkingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for the api Toll Parking
 */
@Api(tags = {"TollParking"})
@SwaggerDefinition(tags = @Tag(name = "TollParking", description = "API to manage the toll parking"))
@RestController
@RequestMapping("/tollparking")
public class TollParkingController {

    @Autowired
    private ParkingService parkingService;

    @ApiOperation(value = "Initialization of the parking", notes = "A service to initialize the configuration for the parking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Parking initialization succeed"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "/initparking")
    public ResponseEntity<?> initParking(@RequestBody @Valid InitParkingDtoIn initParkingDtoIn) {
        parkingService.initialize(initParkingDtoIn);
        return ResponseEntity.ok("Parking initialization succeed");
    }

    @ApiOperation(value = "Checkin of a customer car in the parking", notes = "A service to hundle the entering of a customer car in the parking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Checkin succeed"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "/checkin")
    public ResponseEntity<CheckinDtoOut> checkIn(@RequestBody @Valid CheckinDtoIn checkinDtoIn) throws SlotNotFoundException, ConfigurationNotFoundException {
        CheckinDtoOut checkinDtoOut = parkingService.park(checkinDtoIn);
        return new ResponseEntity<>(checkinDtoOut, HttpStatus.OK);
    }

    @ApiOperation(value = "Checkout of a customer car from the parking", notes = "A service to hundle when a customer car leaves the parking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Checkout succeed"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "/checkout")
    public ResponseEntity<?> checkout(@RequestBody @Valid CheckoutDtoIn checkoutDtoIn) throws TicketNotFoundException, FunctionalException, ConfigurationNotFoundException {
        parkingService.exit(checkoutDtoIn);
        return ResponseEntity.ok("Checkout is done!");
    }

    @ApiOperation(value = "Billing after the checkout of a customer car from the parking", notes = "A service to bill a customer car after leaving the parking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Billing succeed"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "/bill")
    public ResponseEntity<BillDtoOut> bill(@RequestBody @Valid BillDtoIn billDtoIn) throws TicketNotFoundException, PricingNotFoundException, ConfigurationNotFoundException, FunctionalException {
        BillDtoOut billDtoOut = parkingService.bill(billDtoIn);
        return new ResponseEntity<>(billDtoOut, HttpStatus.OK);
    }
}
