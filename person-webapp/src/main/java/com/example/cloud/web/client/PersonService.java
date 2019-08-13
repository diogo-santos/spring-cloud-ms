package com.example.cloud.web.client;

import com.example.cloud.web.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="GATEWAY-SERVER/person-service", fallback = PersonServiceFallbackImpl.class)
public interface PersonService {
    @RequestMapping(value="/person", method= RequestMethod.GET)
    List<Person> findAll();

    @RequestMapping(value="/person/{id}", method = RequestMethod.GET)
    Person findById(@PathVariable("id") long id);

    @RequestMapping(value="/person", method = RequestMethod.POST)
    Person create(@RequestBody Person person);
}