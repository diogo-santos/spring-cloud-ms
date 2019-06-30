package com.example.web.client;

import com.example.web.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Component
public class PersonServiceFallbackImpl implements PersonService {
    private static Person PERSON_NOT_AVAILABLE = new Person(null, "Person data not available","", emptyList());

    @Override
    public List<Person> findAll() {
        return singletonList(PERSON_NOT_AVAILABLE);
    }

    @Override
    public Person findById(long id) {
        return PERSON_NOT_AVAILABLE;
    }

    @Override
    public Person create(Person person) {
        return null;
    }
}