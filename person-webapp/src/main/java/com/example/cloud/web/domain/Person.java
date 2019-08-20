package com.example.cloud.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.isEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    private Long id;
    @Size(max = 20)
    @NotBlank
    private String firstName;
    @Size(max = 20)
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @Size(max = 20)
    private String other;
    @Size(max = 20)
    private String phone;

    private List<Contact> contacts;

    public void setContactList() {
        contacts = Stream.of(email, phone, other)
                .filter(contact -> !isEmpty(contact))
                .map(Contact::new)
                .collect(toList());
    }
}