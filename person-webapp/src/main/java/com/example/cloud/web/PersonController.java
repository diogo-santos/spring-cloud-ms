package com.example.cloud.web;

import com.example.cloud.web.client.PersonService;
import com.example.cloud.web.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Collections.singletonList;

@Controller
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;
    private static final String PERSON_PAGE = "person";

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping({"/","/person"})
    public String getPeople(Model model, @RequestParam(required = false) Long id) {
        Person person;
        if (null != id && null != (person = personService.findById(id))) {
            model.addAttribute(singletonList(person));
        } else {
            Person[] people = personService.findAll();
            if (null != people) {
                model.addAttribute(people);
            }
        }
        model.addAttribute(new Person());
        return PERSON_PAGE;
    }

    @PostMapping("/person")
    public String createPerson(Model model, @Valid Person person, BindingResult result) {
        if (!result.hasErrors()) {
            person.setContactList();
            Person personSaved = personService.create(person);
            logger.debug("Person saved {}", personSaved);
            return "redirect:/"+PERSON_PAGE;
        }
        return PERSON_PAGE;
    }
}