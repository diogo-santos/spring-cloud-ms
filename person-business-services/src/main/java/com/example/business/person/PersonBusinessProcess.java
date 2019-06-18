package com.example.business.person;

import com.example.business.person.client.ContactService;
import com.example.business.person.client.PersonService;
import com.example.business.person.domain.Contact;
import com.example.business.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class PersonBusinessProcess {

    private PersonService personService;
    private ContactService contactService;

    @Autowired
    public PersonBusinessProcess(PersonService personService, ContactService contactService) {
        this.personService = personService;
        this.contactService = contactService;
    }

    public List<Person> getPeopleWithContactList() {
        List<Person> people = this.personService.findAll();
        List<Contact> contacts = this.contactService.findAll();
        Map<Long, List<String>> contactMap = contacts.stream().collect(groupingBy(Contact::getIdPerson, mapping(Contact::getInfo, toList())));
        people.forEach(person -> person.setContacts(contactMap.getOrDefault(person.getId(), Collections.emptyList())));

        return people;
    }

    public Person getPersonWithContactList(long id) {
        List<Contact> contacts = this.contactService.findByIdPerson(id);
        Person person = this.personService.findById(id);
        person.setContacts(contacts.stream().map(Contact::getInfo).collect(toList()));

        return person;
    }
}