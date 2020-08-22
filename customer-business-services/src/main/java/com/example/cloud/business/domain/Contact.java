package com.example.cloud.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    private Long id;
    private Long idCustomer;
    private String description;

    public static Contact from(final Contact contact, final long idCustomer) {
        return Contact.builder()
                .idCustomer(idCustomer)
                .description(contact.getDescription())
                .build();
    }
}