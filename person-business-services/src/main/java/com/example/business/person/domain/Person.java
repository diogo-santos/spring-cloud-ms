package com.example.business.person.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private long id;
    private String firstName;
    private String lastName;
}