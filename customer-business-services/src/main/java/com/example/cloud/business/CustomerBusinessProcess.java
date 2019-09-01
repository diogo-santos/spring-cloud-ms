package com.example.cloud.business;

import com.example.cloud.business.client.ContactService;
import com.example.cloud.business.client.CustomerService;
import com.example.cloud.business.domain.Contact;
import com.example.cloud.business.domain.Customer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class CustomerBusinessProcess {
    private CustomerService customerService;
    private ContactService contactService;

    @Autowired
    public CustomerBusinessProcess(CustomerService customerService, ContactService contactService) {
        this.customerService = customerService;
        this.contactService = contactService;
    }

    public List<Customer> getCustomersWithContactList() {
        List<Customer> customers = this.customerService.findAll();
        List<Contact> contacts = this.contactService.findAll();
        if (!isEmpty(contacts)) {
            Map<Long, List<Contact>> contactMap = contacts.stream().collect(groupingBy(Contact::getIdCustomer));
            customers.forEach(customer -> customer.setContacts(contactMap.getOrDefault(customer.getId(), emptyList())));
        }
        return customers;
    }

    public Customer getCustomerWithContactList(long id) {
        Customer customer = this.customerService.findById(id);
        if (customer!=null) {
            List<Contact> contacts = this.contactService.findByIdCustomer(id);
            customer.setContacts(contacts);
        }
        return customer;
    }

    public Customer createCustomerWithContactList(Customer customer) {
        final Customer customerSaved = this.customerService.create(customer);
        if (null != customerSaved && !isEmpty(customer.getContacts())) {
            customer.getContacts().removeIf(contact -> contact == null || StringUtils.isEmpty(contact.getInfo()));
            customer.getContacts().forEach(contact -> contact.setIdCustomer(customerSaved.getId()));

            customerSaved.setContacts(this.contactService.create(customer.getContacts()));
        }
        return customerSaved;
    }
}