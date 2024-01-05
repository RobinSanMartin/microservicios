package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.entity.Region;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAllCustomer();
    public Customer findCustomerById(Long id);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Long id);
    public Customer findCustomerByRut(String rut);
    public List<Customer> findCustomerByLastName(String lastName);
    public List<Customer> findCustomerByRegion(Region region);
}
