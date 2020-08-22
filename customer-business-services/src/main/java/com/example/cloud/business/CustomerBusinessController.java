package com.example.cloud.business;

import com.example.cloud.business.domain.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="CustomerBusiness", tags=("customerBusiness"))
public class CustomerBusinessController {

    private CustomerBusinessProcess businessProcess;

    @Autowired
    public CustomerBusinessController(CustomerBusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
    }

    @GetMapping("/customers")
    @ApiOperation(value="Get all Customer", notes="Gets all customers with contact list in the system", nickname="getCustomers")
    public List<Customer> getCustomers() {
        return businessProcess.getCustomersWithContactList();
    }

    @GetMapping("/customers/{id}")
    @ApiOperation(value = "Get customer", notes = "Get a single customer with contact list based on its unique id", nickname = "getCustomer")
    public Customer getCustomer(@PathVariable long id) {
        return businessProcess.getCustomerWithContactList(id);
    }

    @PostMapping("/customers")
    @ApiOperation(value = "Create customer", notes = "Create customer with contact list", nickname = "createCustomer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return businessProcess.createCustomerWithContactList(customer);
    }
}