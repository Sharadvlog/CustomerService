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
    private Integer addressId;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable  = false)
    private Customer customer;


    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", line1='" + line1 + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }
}
