package com.example.cloud.business;

import com.example.cloud.business.client.ContactService;
import com.example.cloud.business.client.CustomerService;
import com.example.cloud.business.domain.Contact;
import com.example.cloud.business.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class CustomerBusinessProcess {
    private final CustomerService customerService;
    private final ContactService contactService;

    @Autowired
    CustomerBusinessProcess(CustomerService customerService, ContactService contactService) {
        this.customerService = customerService;
        this.contactService = contactService;
    }

    List<Customer> getCustomersWithContactList() throws Throwable {
        CompletableFuture<List<Customer>> customersAsync = this.getCustomers();
        CompletableFuture<List<Contact>> contactsAsync = this.getContacts();

        List<Contact> contacts = contactsAsync.get();
        List<Customer> customers = customersAsync.get();
        if (!isEmpty(contacts) && !isEmpty(customers)) {
            Map<Long, List<Contact>> contactMap = contacts.stream()
                    .collect(groupingBy(Contact::getIdCustomer));

            customers.forEach(customer -> {
                List<Contact> customerContacts = contactMap.getOrDefault(customer.getId(), emptyList());
                customer.setContacts(customerContacts);
            });
        }
        return customers;
    }

    Customer getCustomerWithContactList(final long id) throws Throwable {
        CompletableFuture<Customer> customerAsync = this.getCustomer(id);
        CompletableFuture<List<Contact>> contactsAsync = this.getContacts(id);

        Customer customer = customerAsync.get();
        if (nonNull(customer)) {
            customer.setContacts(contactsAsync.get());
        }
        return customer;
    }

    Customer createCustomerWithContactList(final Customer customer) {
        Customer customerSaved = this.customerService.create(customer);
        if (nonNull(customerSaved) && !isEmpty(customer.getContacts())) {
            List<Contact> contactsToSave = customer.getContacts().stream()
                    .filter(Objects::nonNull)
                    .filter(contact -> isNotBlank(contact.getDescription()))
                    .map(contact -> Contact.from(contact, customerSaved.getId()))
                    .collect(toList());

            if (!isEmpty(contactsToSave)) {
                List<Contact> contacts = this.contactService.create(contactsToSave);
                customerSaved.setContacts(contacts);
            }
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