package com.example.business.person.client;

import com.example.business.person.domain.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="PERSONCONTACTSERVICES", fallback = ContactServiceFallbackImpl.class)
public interface ContactService {
    @RequestMapping(value="/contact", method= RequestMethod.GET)
    List<Contact> findAll();

    @RequestMapping(value="/contact/{idPerson}", method = RequestMethod.GET)
    List<Contact> findByIdPerson(@PathVariable("idPerson") long idPerson);

    @RequestMapping(value="/contact", method = RequestMethod.POST)
    List<Contact> create(@RequestBody List<Contact> contacts);
}