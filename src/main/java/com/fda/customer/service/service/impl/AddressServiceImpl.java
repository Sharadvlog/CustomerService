package com.fda.customer.service.service.impl;

import com.fda.customer.service.dto.AddressDto;
import com.fda.customer.service.entity.Address;
import com.fda.customer.service.repo.AddressRepository;
import com.fda.customer.service.repo.CustomerRepository;
import com.fda.customer.service.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDto> getAddressByCustomerId(Integer customerId) {
        List<Address> addressList = addressRepository.findByCustomerId(customerId);
        return addressList.stream().map(address -> {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddressId(String.valueOf(address.getAddressId()));
            addressDto.setCustomerId(String.valueOf(address.getCustomer().getCustomerId()));
            addressDto.setCreatedAt(String.valueOf(address.getCreatedAt()));
            addressDto.setPincode(address.getPinCode());
            addressDto.setCity(address.getCity());
            addressDto.setLine1(address.getLine1());
            addressDto.setArea(address.getArea());
            return addressDto;
        }).toList();
    }

    @Override
    public AddressDto addNewAddressForCustomer(Integer customerId, Address address) {
        address.setCustomer(customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found")));
        Address address1 = addressRepository.save(address);
        AddressDto addressDto = convertTOAddressDto(address1);
        addressDto.setCustomerId(String.valueOf(customerId));
        return addressDto;
    }

    @Override
    public AddressDto updateAddressForCustomer(Integer customerId, Integer addressId, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isEmpty()) {
            System.out.printf("Address with id {} not found for customer {}", addressId, customerId);
        }
        Address existingAddress = optionalAddress.get();
        existingAddress.setLine1(address.getLine1());
        existingAddress.setArea(address.getArea());
        existingAddress.setCity(address.getCity());
        existingAddress.setPinCode(address.getPinCode());
        Address address1 = addressRepository.save(existingAddress);
        return convertTOAddressDto(address1);

    }

    @Transactional
    @Override
    public void deleteAddressForCustomer(Integer customerId, Integer addressId) {
        addressRepository.deleteCustomerAddressById(customerId, addressId);
    }

    private AddressDto convertTOAddressDto(Address address1) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(String.valueOf(address1.getAddressId()));
        addressDto.setCreatedAt(String.valueOf(address1.getCreatedAt()));
        addressDto.setPincode(address1.getPinCode());
        addressDto.setCity(address1.getCity());
        addressDto.setLine1(address1.getLine1());
        addressDto.setArea(address1.getArea());
        addressDto.setCustomerId(String.valueOf(address1.getCustomer().getCustomerId()));
        return addressDto;
    }
}
