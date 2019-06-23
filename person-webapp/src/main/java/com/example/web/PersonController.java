package com.example.web;

import com.example.web.client.PersonService;
import com.example.web.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class PersonController {
    private final PersonService personService;
    private static final String PERSON_PAGE = "person";

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public String getPeople(Model model, @RequestParam(required = false) Long id) {
        Person findPerson;
        if (null!=id && null!=(findPerson = personService.findById(id))) {
            model.addAttribute(Collections.singletonList(findPerson));
        } else {
            model.addAttribute(personService.findAll());
        }
        model.addAttribute(new Person());
        return PERSON_PAGE;
    }

    @PostMapping("/person")
    public String createPerson(Model model, @Valid Person person, BindingResult result) {
        if (!result.hasErrors()) {
            person.getContacts().removeIf(c->StringUtils.isEmpty(c.getInfo()));
            personService.create(person);
            return "redirect:/"+PERSON_PAGE;
        }
        return PERSON_PAGE;
    }
}