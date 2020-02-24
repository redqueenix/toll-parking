package com.assignment.controller;

import com.assignment.dto.*;
import com.assignment.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tollparking")
public class TollParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping(value = "/initparking")
    public ResponseEntity<?> initParking(@RequestBody @Valid InitParkingDtoIn initParkingDtoIn) {
        parkingService.initialize(initParkingDtoIn);
        return ResponseEntity.ok("Parking initialization succeed");
    }

    @PostMapping(value = "/checkin")
    public ResponseEntity<CheckinDtoOut> checkIn(@RequestBody @Valid CheckinDtoIn checkinDtoIn) {
        CheckinDtoOut checkinDtoOut = parkingService.park(checkinDtoIn);
        return new ResponseEntity<>(checkinDtoOut, HttpStatus.OK);
    }

    @PostMapping(value = "/checkout")
    public ResponseEntity<?> checkIn(@RequestBody @Valid CheckoutDtoIn checkoutDtoIn) {
        parkingService.exit(checkoutDtoIn);
        return ResponseEntity.ok("Checkout is done!");
    }

    @PostMapping(value = "/bill")
    public ResponseEntity<BillDtoOut> bill(@RequestBody @Valid CheckoutDtoIn checkoutDtoIn) {
        BillDtoOut billDtoOut = parkingService.bill(checkoutDtoIn);
        return new ResponseEntity<>(billDtoOut, HttpStatus.OK);
    }
}
