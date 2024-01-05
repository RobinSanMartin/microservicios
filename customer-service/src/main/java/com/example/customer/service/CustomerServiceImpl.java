package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.entity.Region;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDb = customerRepository.findByRut(customer.getRut());

        if(customerDb != null){
            return customerDb;
        }

        customer.setState("CREATED");
        customer.setCreatedAt(new Date());

        customerDb = customerRepository.save(customer);

        return customerDb;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDb = findCustomerById(customer.getId());

        if(customerDb == null){
            return null;
        }

        customerDb.setFirstName(customer.getFirstName());
        customerDb.setLastName(customer.getLastName());
        customerDb.setEmail(customer.getEmail());
        customerDb.setPhotoUrl(customer.getPhotoUrl());
        customerDb.setRegion(customer.getRegion());

        return customerRepository.save(customerDb);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customerDb = findCustomerById(id);

        if(customerDb == null){
            return null;
        }

        customerDb.setState("DELETED");

        return customerRepository.save(customerDb);
    }

    @Override
    public Customer findCustomerByRut(String rut) {
        return customerRepository.findByRut(rut);
    }

    @Override
    public List<Customer> findCustomerByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public List<Customer> findCustomerByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }
}
