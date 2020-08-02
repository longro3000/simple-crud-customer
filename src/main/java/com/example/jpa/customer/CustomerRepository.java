package com.example.jpa.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByIsActiveTrue();
  Optional<Customer> findByUuid(UUID uuid);
}
