package com.example.cloud.business.client;

import com.example.cloud.business.domain.Contact;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ContactServiceFallbackImpl implements ContactService {
    @Override
    public List<Contact> getContacts() {
        return Collections.emptyList();
    }

    @Override
    public List<Contact> getContacts(long idCustomer) {
        return Collections.singletonList(new Contact(null, null, "Not Available"));
    }

    @Override
    public List<Contact> create(List<Contact> contacts) {
        return Collections.emptyList();
    }
}