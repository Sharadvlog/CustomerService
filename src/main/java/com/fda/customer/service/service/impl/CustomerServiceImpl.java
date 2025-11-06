package com.fda.customer.service.service.impl;

import com.fda.customer.service.dto.CustomerDto;
import com.fda.customer.service.entity.Customer;
import com.fda.customer.service.repo.CustomerRepository;
import com.fda.customer.service.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(customer.getCustomerId());
                    customerDto.setName(customer.getName());
                    customerDto.setEmail(customer.getEmail());
                    customerDto.setPhone(customer.getPhone());
                    customerDto.setCreatedAt(customer.getCreatedAt());
                    return customerDto;
                }
                ).toList();
    }

    @Override
    public boolean existById(Integer cutomerId) {
        return customerRepository.existsById(cutomerId);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        return customerRepository.findById(customerId);
    }
}