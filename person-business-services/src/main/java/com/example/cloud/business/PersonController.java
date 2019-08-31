package com.example.cloud.business;

import com.example.cloud.business.domain.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="PersonBusiness", tags=("personBusiness"))
public class PersonController {

    private PersonBusinessProcess businessProcess;

    @Autowired
    public PersonController(PersonBusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
    }

    @GetMapping("/person")
    @ApiOperation(value="Get all People", notes="Gets all people with contact list in the system", nickname="getPeople")
    public List<Person> getPeople() {
        return businessProcess.getPeopleWithContactList();
    }

    @GetMapping("/person/{id}")
    @ApiOperation(value = "Get person", notes = "Get a single person with contact list based on its unique id", nickname = "getPerson")
    public Person getPerson(@PathVariable long id) {
        return businessProcess.getPersonWithContactList(id);
    }

    @PostMapping("/person")
    @ApiOperation(value = "Create person", notes = "Create person with contact list", nickname = "createPerson")
    public Person createPerson(@RequestBody Person person) {
        return businessProcess.createPersonWithContactList(person);
    }
}