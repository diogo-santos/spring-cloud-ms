package com.example.cloud.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
public class Person implements Serializable {
    private Long id;
    @Size(max = 20)
    @NotBlank
    private String firstName;
    @Size(max = 20)
    @NotBlank
    private String lastName;
    private List<Contact> contacts;

    public Person() {
        contacts = Stream.generate(Contact::new).limit(3).collect(toList());
    }
}