package com.example.cloud.business.client;

import com.example.cloud.business.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerServiceFallbackImpl implements CustomerService {

    @Override
    public List<Customer> getCustomers() {
        return Collections.emptyList();
    }

    @Override
    public Customer getCustomer(long id) {
        return new Customer(null, "Not", "Available", Collections.emptyList());
    }

    @Override
    public Customer create(Customer customer) {
        return null;
    }
}