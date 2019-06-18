package com.example.business.person;

import com.example.business.person.domain.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="PersonBusiness", tags=("personBusiness"))
public class PersonController {
    @Autowired
    private PersonBusinessProcess businessProcess;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ApiOperation(value="Get all People", notes="Gets all people in the system", nickname="getPeople")
    public List<Person> getAllPeople() {
        return businessProcess.getPeopleWithContactList();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get person", notes = "Get a single person based on its unique id", nickname = "getPerson")
    public Person getPerson(@PathVariable long id) {
        return businessProcess.getPersonWithContactList(id);
    }
}