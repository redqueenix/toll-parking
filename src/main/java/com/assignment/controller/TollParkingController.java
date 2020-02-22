package com.assignment.controller;

import com.assignment.dto.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tollparking")
public class TollParkingController {

    @GetMapping(value = "/action")
    public Greeting myAction(@RequestParam(value = "name", defaultValue = "World") String name) {
        // service call
        return new Greeting(1,"Hello "+name );
    }
}
