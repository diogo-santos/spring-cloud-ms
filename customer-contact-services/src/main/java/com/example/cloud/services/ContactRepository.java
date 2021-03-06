package com.example.cloud.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<List<Contact>> findByIdCustomer(Long idCustomer);
}