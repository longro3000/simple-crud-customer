package com.customer.demo.customer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
  List<Person> findByIsDeleted(boolean status);
  List<Person> findByCustomer(UUID customer)
}
