package com.assignment.controller;

import com.assignment.dto.Customer;
import com.assignment.dto.Greeting;
import com.assignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tollparking")
public class TollParkingController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/action")
    public Customer myAction(@RequestParam(value = "name", defaultValue = "World") String name) {
        // service call
        customerRepository.save(new Customer(name, name+"@domain.com"));
        return customerRepository.findById(1L).get();
    }
}
