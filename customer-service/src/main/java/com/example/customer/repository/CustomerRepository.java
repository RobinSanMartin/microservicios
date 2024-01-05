package com.example.customer.repository;

import com.example.customer.entity.Customer;
import com.example.customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByRut(String rut);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByRegion(Region region);
}
