package com.example.customer.controller;

import com.example.customer.entity.Customer;
import com.example.customer.entity.Region;
import com.example.customer.pojo.ErrorMessage;
import com.example.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer(@RequestParam(name ="regionId", required = false) Long regionId){
        List<Customer> customers;

        if(null == regionId){
            customers = customerService.findAllCustomer();

            if(customers.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            customers = customerService.findCustomerByRegion(Region.builder().id(regionId).build());
            if(customers.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerById(id);
        if(null == customer){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Customer customerCreated = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updacteCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customer.setId(id);
        Customer customerDB = customerService.findCustomerById(id);

        if(customerDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id){
        Customer customerDB = customerService.findCustomerById(id);
        if(customerDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(e -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(e.getField(), e.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try{
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return jsonString;
    }
}
