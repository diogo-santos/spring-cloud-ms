package com.example.cloud.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "contact")
@Api(value = "contact", tags = "contact")
public class ContactController {
    @Autowired
    private ContactRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all contacts", notes = "Get all contacts in the system", nickname = "getContacts")
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @RequestMapping(path = "/{idPerson}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get contacts by person id", notes = "Get contact list based on its person id", nickname = "getContactsByIdPerson")
    public List<Contact> findByIdPerson(@PathVariable long idPerson) {
        return repository.findByIdPerson(idPerson).orElse(Collections.emptyList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create contact", notes = "Create contacts in the system", nickname = "createContact")
    public List<Contact> create(@RequestBody @Validated List<Contact> contacts) {
        return repository.saveAll(contacts);
    }
}