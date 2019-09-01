package com.example.cloud.web.client;

import com.example.cloud.web.domain.Customer;

public interface CustomerService {
    Customer[] findAll();
    Customer findById(long id);
    Customer create(Customer person);
}