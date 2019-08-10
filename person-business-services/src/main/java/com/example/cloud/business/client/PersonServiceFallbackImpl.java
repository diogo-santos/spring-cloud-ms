package com.example.cloud.business.client;

import com.example.cloud.business.domain.Person;
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