package com.fda.customer.service.service;

import com.fda.customer.service.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerDto> getAllCustomers();
}
