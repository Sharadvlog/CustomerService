package com.fda.customer.service.controller;

import com.fda.customer.service.dto.CustomerDto;
import com.fda.customer.service.entity.Customer;
import com.fda.customer.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No customers found in the database.");
        }
        return ResponseEntity.ok(customers);

    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            if (customer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer must not be null.");
            }
            if (customerService.existById(customer.getCustomerId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer with ID " + customer.getCustomerId() + " already exists.");
            }
            Customer savedCustomer = customerService.save(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving customer: " + e.getMessage());
        }
    }

}
