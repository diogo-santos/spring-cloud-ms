package com.example.business.person.client;

import com.example.business.person.domain.Person;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PersonServiceFallbackImpl implements PersonService {

    @Override
    public List<Person> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Person findById(long id) {
        return new Person(null, "Not", "Available", Collections.emptyList());
    }

    @Override
    public Person create(Person person) {
        return null;
    }
}