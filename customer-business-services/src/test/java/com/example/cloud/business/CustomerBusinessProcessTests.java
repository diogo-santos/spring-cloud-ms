package com.example.cloud.business;

import com.example.cloud.business.client.ContactService;
import com.example.cloud.business.client.CustomerService;
import com.example.cloud.business.domain.Contact;
import com.example.cloud.business.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerBusinessProcessTests {

    @InjectMocks
    private CustomerBusinessProcess businessProcess;

    private CustomerService customerService;

    private ContactService contactService;


    @Before
    public void setup() {
        this.customerService = mock(CustomerService.class);
        this.contactService = mock(ContactService.class);
        this.businessProcess = new CustomerBusinessProcess(customerService, contactService);
    }

    @Test
    public void givenCustomersAndContactsWhenGetAllCustomersThenReturnCustomersWithContactList() {
        //GIVEN
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Customer1");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Customer2");

        Contact contact1 = new Contact(1L, customer1.getId(), "email");
        Contact contact2 = new Contact(2L, customer2.getId(), "emailCustomer2");
        Contact contact3 = new Contact(3L, customer2.getId(), "otherCustomer2");
        Contact contact4 = new Contact(4L, 3L, "invalid");

        when(customerService.getCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        when(contactService.getContacts()).thenReturn(Arrays.asList(contact1, contact2, contact3, contact4));

        List<Customer> expectedResult = new ArrayList<>();
        customer1.setContacts(singletonList(contact1));
        expectedResult.add(customer1);
        customer2.setContacts(singletonList(contact2));
        expectedResult.add(customer2);

        //WHEN
        List<Customer> result = businessProcess.getCustomersWithContactList();

        //THEN
        assertEquals(expectedResult, result);
    }

    @Test
    public void givenCustomerIdWhenGetCustomerByIdThenReturnCustomerWithContactList() {
        //GIVEN
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Customer2");

        Contact contact1 = new Contact(2L, customer.getId(), "emailCustomer2");
        Contact contact2 = new Contact(3L, customer.getId(), "otherCustomer2");

        when(customerService.getCustomer(anyLong())).thenReturn(customer);

        when(contactService.getContacts(anyLong())).thenReturn(Arrays.asList(contact1, contact2));

        Customer expectedResult = new Customer();
        expectedResult.setFirstName(customer.getFirstName());
        expectedResult.setId(customer.getId());
        expectedResult.setContacts(Arrays.asList(contact1, contact2));

        //WHEN
        Customer result = businessProcess.getCustomerWithContactList(customer.getId());

        //THEN
        assertEquals(expectedResult, result);
    }
}
