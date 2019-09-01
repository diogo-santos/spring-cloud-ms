package com.example.cloud.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@Api(value = "customers", tags = "customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all customers", notes = "Get all customers in the system", nickname = "getCustomers")
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get customer", notes = "Get a single customer based on its unique id", nickname = "getCustomer")
    public Customer findById(@PathVariable long id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Customer", notes = "Create a single customer in the system", nickname = "createCustomer")
    public Customer create(@RequestBody @Validated Customer customer) {
        return repository.save(customer);
    }
}