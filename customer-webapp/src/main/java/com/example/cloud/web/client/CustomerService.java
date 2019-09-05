package com.example.cloud.web.client;

import com.example.cloud.web.domain.Customer;

public interface CustomerService {
    Customer[] getCustomers();
    Customer getCustomer(long id);
    Customer create(Customer customer);
}