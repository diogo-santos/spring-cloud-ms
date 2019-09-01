package com.example.cloud.business.client;

import com.example.cloud.business.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerServiceFallbackImpl implements CustomerService {

    @Override
    public List<Customer> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Customer findById(long id) {
        return new Customer(null, "Not", "Available", Collections.emptyList());
    }

    @Override
    public Customer create(Customer person) {
        return null;
    }
}