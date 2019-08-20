package com.example.cloud.web.client;

import com.example.cloud.web.domain.Person;

public interface PersonService {
    Person[] findAll();
    Person findById(long id);
    Person create(Person person);
}