package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> findAll() {
        return (List<Person>) repository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Person findOne(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody @Validated PersonDto person) {
        return repository.save(new Person(null, person.getFirstName(), person.getLastName()));
    }
}