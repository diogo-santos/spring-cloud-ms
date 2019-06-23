package com.example.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Long id;
    private Long idPerson;
    @NotBlank
    private String info;
}