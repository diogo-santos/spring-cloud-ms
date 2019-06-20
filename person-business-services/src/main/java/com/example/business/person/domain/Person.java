package com.example.business.person.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Contact> contacts;
}