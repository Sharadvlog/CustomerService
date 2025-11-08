package com.fda.customer.service.repo;

import com.fda.customer.service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address a WHERE a.customer.customerId = :customerId")
    List<Address> findByCustomerId(@Param("customerId") Integer customerId);

    @Modifying
    @Query("DELETE FROM Address a WHERE a.customer.customerId = :customerId AND a.addressId = :addressId")
    void deleteCustomerAddressById(@Param("customerId") Integer customerId, @Param("addressId") Integer addressId);
}
