package com.fda.customer.service.service;

import com.fda.customer.service.dto.AddressDto;
import com.fda.customer.service.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<AddressDto> getAddressByCustomerId(Integer valueOf);

    AddressDto addNewAddressForCustomer(Integer valueOf, Address address);

    AddressDto updateAddressForCustomer(Integer customerId, Integer AddressId, Address address);

    void deleteAddressForCustomer(Integer customerId, Integer addressId);
}
