package com.fda.customer.service.controller;

import com.fda.customer.service.dto.AddressDto;
import com.fda.customer.service.entity.Address;
import com.fda.customer.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/{customer_id}/addresses")
    public ResponseEntity<?> getAddressesByCustomerId(@PathVariable("customer_id") String customerId) {

        List<AddressDto> addressByCustomerId = addressService.getAddressByCustomerId(Integer.valueOf(customerId));

        if (addressByCustomerId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with ID " + customerId + " not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(addressByCustomerId);
    }

    @PostMapping(value = "/{customer_id}/addresses")
    public ResponseEntity<?> addAddressForCustomer(@PathVariable("customer_id") String customerId, @RequestBody Address address) {
        AddressDto savedAddress = addressService.addNewAddressForCustomer(Integer.valueOf(customerId), address);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(savedAddress);
    }

    @PutMapping(value = "/{customer_id}/addresses/{address_id}")
    public ResponseEntity<?> updateAddressForCustomer(@PathVariable("customer_id") String customerId,
                                                      @PathVariable("address_id") String addressId,
                                                      @RequestBody Address address) {
        AddressDto addressDto = addressService.updateAddressForCustomer(Integer.valueOf(customerId), Integer.valueOf(addressId), address);
        return ResponseEntity.status(HttpStatus.OK).body(addressDto);
    }

    @DeleteMapping(value = "/{customer_id}/addresses/{address_id}")
    public ResponseEntity<?> deleteAddressForCustomer(@PathVariable("customer_id") String customerId,
                                                      @PathVariable("address_id") String addressId) {
        addressService.deleteAddressForCustomer(Integer.valueOf(customerId), Integer.valueOf(addressId));
        return ResponseEntity.status(HttpStatus.OK).body("Delete address for Customer ID " + customerId + " and Address ID " + addressId + " successful.");
    }

}

