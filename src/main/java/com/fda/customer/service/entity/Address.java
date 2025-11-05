package com.fda.customer.service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @Column(name = "address_id")
    private String addressId;

    @Column(name = "line1")
    private String line1;

    @Column(name = "area")
    private String area;
    @Column(name = "city")
    private String city;
    @Column(name = "pincode")
    private String pinCode;

    @Column(name = "created_at")
    private LocalDate createdAt;

//    @Column(name = "customer_id" , insertable = false, updatable = false)
//    private String customerId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable  = false)
    private Customer customer;


}
