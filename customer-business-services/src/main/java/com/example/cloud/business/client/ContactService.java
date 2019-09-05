package com.example.cloud.business.client;

import com.example.cloud.business.domain.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="CUSTOMERCONTACTSERVICES", fallback = ContactServiceFallbackImpl.class)
public interface ContactService {
    @GetMapping("/contacts")
    List<Contact> getContacts();

    @GetMapping("/contacts/{idCustomer}")
    List<Contact> getContacts(@PathVariable("idCustomer") long idCustomer);

    @PostMapping("/contacts")
    List<Contact> create(@RequestBody List<Contact> contacts);
}