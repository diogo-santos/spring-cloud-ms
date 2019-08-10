package com.example.cloud.business;

import com.example.cloud.business.client.ContactService;
import com.example.cloud.business.client.PersonService;
import com.example.cloud.business.domain.Contact;
import com.example.cloud.business.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.springframework.util.CollectionUtils.isEmpty;

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
        if (!isEmpty(contacts)) {
            Map<Long, List<Contact>> contactMap = contacts.stream().collect(groupingBy(Contact::getIdPerson));
            people.forEach(person -> person.setContacts(contactMap.getOrDefault(person.getId(), Collections.emptyList())));
        }
        return people;
    }

    public Person getPersonWithContactList(long id) {
        Person person = this.personService.findById(id);
        if (person!=null) {
            List<Contact> contacts = this.contactService.findByIdPerson(id);
            person.setContacts(contacts);
        }
        return person;
    }

    public Person createPersonWithContactList(Person person) {
        final Person personSaved = this.personService.create(person);
        if (null!=person.getContacts() && !person.getContacts().isEmpty()) {
            person.getContacts().forEach(c->c.setIdPerson(personSaved.getId()));
            personSaved.setContacts(this.contactService.create(person.getContacts()));
        }
        return personSaved;
    }
}