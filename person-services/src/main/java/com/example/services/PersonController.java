package com.example.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
@Api(value = "person", tags = "person")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all people", notes = "Get all people in the system", nickname = "getPeople")
    public List<Person> findAll() {
        return (List<Person>) repository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get person", notes = "Get a single person based on its unique id", nickname = "getPerson")
    public Person findById(@PathVariable long id){
        return repository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create person", notes = "Create a single person in the system", nickname = "createPerson")
    public Person create(@RequestBody @Validated Person person) {
        return repository.save(person);
    }
}