package com.example.cloud.business;

import com.example.cloud.business.client.ContactService;
import com.example.cloud.business.client.CustomerService;
import com.example.cloud.business.domain.Contact;
import com.example.cloud.business.domain.Customer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
class CustomerBusinessProcess {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;
    private ContactService contactService;

    @Autowired
    CustomerBusinessProcess(CustomerService customerService, ContactService contactService) {
        this.customerService = customerService;
        this.contactService = contactService;
    }

    List<Customer> getCustomersWithContactList() {
        CompletableFuture<List<Customer>> customersFuture = this.getCustomers();
        CompletableFuture<List<Contact>> contactsFuture = this.getContacts();

        CompletableFuture<Void> allOfFutures = CompletableFuture.allOf(customersFuture, contactsFuture);
        allOfFutures.exceptionally(e -> {logger.error(e.getMessage(), e); return null;});

        List<Customer> customers;
        List<Contact> contacts;
        if (!isEmpty(customers = customersFuture.join())
                && !isEmpty(contacts = contactsFuture.join())) {
            Map<Long, List<Contact>> contactMap = contacts.stream().collect(groupingBy(Contact::getIdCustomer));
            customers.forEach(customer -> customer.setContacts(contactMap.getOrDefault(customer.getId(), emptyList())));
        }
        return customers;
    }

    Customer getCustomerWithContactList(long id) {
        CompletableFuture<Customer> customerFuture = this.getCustomer(id);
        CompletableFuture<List<Contact>> contactsFuture = this.getContacts(id);

        CompletableFuture<Void> allOfFutures = CompletableFuture.allOf(customerFuture, contactsFuture);
        allOfFutures.exceptionally(e -> {logger.error(e.getMessage(), e); return null;});

        Customer customer;
        if (null != (customer = customerFuture.join())) {
            customer.setContacts(contactsFuture.join());
        }
        return customer;
    }

    Customer createCustomerWithContactList(Customer customer) {
        final Customer customerSaved = this.customerService.create(customer);
        if (null != customerSaved && !isEmpty(customer.getContacts())) {
            customer.getContacts().removeIf(contact -> contact == null || StringUtils.isEmpty(contact.getInfo()));
            customer.getContacts().forEach(contact -> contact.setIdCustomer(customerSaved.getId()));

            customerSaved.setContacts(this.contactService.create(customer.getContacts()));
        }
        return customerSaved;
    }

    @Async
    private CompletableFuture<List<Customer>> getCustomers() {
        return CompletableFuture.completedFuture(this.customerService.getCustomers());
    }

    @Async
    private CompletableFuture<Customer> getCustomer(long id) {
        return CompletableFuture.completedFuture(this.customerService.getCustomer(id));
    }

    @Async
    private CompletableFuture<List<Contact>> getContacts() {
        return CompletableFuture.completedFuture(this.contactService.getContacts());
    }

    @Async
    private CompletableFuture<List<Contact>> getContacts(long idCustomer) {
        return CompletableFuture.completedFuture(this.contactService.getContacts(idCustomer));
    }
}