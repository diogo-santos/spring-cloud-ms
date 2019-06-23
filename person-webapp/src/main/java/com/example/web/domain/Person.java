package com.example.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
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
}