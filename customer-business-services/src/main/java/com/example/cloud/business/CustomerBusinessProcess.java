package com.example.cloud.business;

import com.example.cloud.business.client.ContactService;
import com.example.cloud.business.client.CustomerService;
import com.example.cloud.business.domain.Contact;
import com.example.cloud.business.domain.Customer;
import org.apache.commons.lang.StringUtils;
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
public class CustomerBusinessProcess {
    private CustomerService customerService;
    private ContactService contactService;

    @Autowired
    CustomerBusinessProcess(CustomerService customerService, ContactService contactService) {
        this.customerService = customerService;
        this.contactService = contactService;
    }

    List<Customer> getCustomersWithContactList() throws Throwable {
        CompletableFuture<List<Customer>> customersAsync = this.getCustomers();
        CompletableFuture<List<Contact>> contactsAsync = this.getContacts();

        List<Customer> customers = null;
        List<Contact> contacts;
        if (!isEmpty(contacts = contactsAsync.get()) && !isEmpty(customers = customersAsync.get())) {
            Map<Long, List<Contact>> contactMap = contacts.stream().collect(groupingBy(Contact::getIdCustomer));
            customers.forEach(customer -> customer.setContacts(contactMap.getOrDefault(customer.getId(), emptyList())));
        }
        return customers;
    }

    Customer getCustomerWithContactList(long id) throws Throwable {
        CompletableFuture<Customer> customerAsync = this.getCustomer(id);
        CompletableFuture<List<Contact>> contactsAsync = this.getContacts(id);

        Customer customer;
        if (null != (customer = customerAsync.get())) {
            customer.setContacts(contactsAsync.get());
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