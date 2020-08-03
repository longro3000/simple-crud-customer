package com.example.jpa.person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
  List<Person> findByIsDeleted(boolean status);
  Optional<Person> findByUuid(UUID uuid);
}
