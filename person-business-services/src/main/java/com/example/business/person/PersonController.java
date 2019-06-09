package com.example.business.person;

import com.example.business.person.client.PersonService;
import com.example.business.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getAllPeople() {
        return personService.findAll();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") long id) {
        return personService.findById(id);
    }
}