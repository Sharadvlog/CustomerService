package com.fda.customer.service.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String customerId;
    private String addressId;
    private String line1;
    private String area;
    private String city;
    private String pincode;
    private String createdAt;

}
