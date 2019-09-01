package com.example.cloud.business.client;

import com.example.cloud.business.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="CUSTOMERSERVICES", fallback = CustomerServiceFallbackImpl.class)
public interface CustomerService {
    @GetMapping("/customers")
    List<Customer> findAll();

    @GetMapping("/customers/{id}")
    Customer findById(@PathVariable("id") long id);

    @PostMapping("/customers")
    Customer create(@RequestBody Customer customer);
}