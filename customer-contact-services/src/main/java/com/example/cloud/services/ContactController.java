package com.example.cloud.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("contacts")
@Api(value = "contacts", tags = "contacts")
public class ContactController {

    private ContactRepository repository;

    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all contacts", notes = "Get all contacts in the system", nickname = "getContacts")
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{idCustomer}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get contacts by customer id", notes = "Get contact list based on its customer id", nickname = "getContactsByIdCustomer")
    public List<Contact> findByIdCustomer(@PathVariable long idCustomer) {
        return repository.findByIdCustomer(idCustomer).orElse(Collections.emptyList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create contact", notes = "Create contacts in the system", nickname = "createContact")
    public List<Contact> create(@RequestBody @Validated List<Contact> contacts) {
        return repository.saveAll(contacts);
    }
}