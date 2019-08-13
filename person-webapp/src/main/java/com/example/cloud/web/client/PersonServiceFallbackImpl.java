package com.example.cloud.web.client;

import com.example.cloud.web.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Component
public class PersonServiceFallbackImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceFallbackImpl.class);
    private static final Person PERSON_NOT_AVAILABLE = new Person(null, "Person data not available","", emptyList());

    @Override
    public List<Person> findAll() {
        logger.debug("Fallback findAll");
        return singletonList(PERSON_NOT_AVAILABLE);
    }

    @Override
    public Person findById(long id) {
        logger.debug("Fallback findById {}", id);
        return PERSON_NOT_AVAILABLE;
    }

    @Override
    public Person create(Person person) {
        logger.debug("Fallback create {}", person);
        return null;
    }
}