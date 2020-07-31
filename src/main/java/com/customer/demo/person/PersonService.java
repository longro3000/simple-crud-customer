package com.customer.demo.person;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.example.jpa.exception.ResourceNotFoundException;

@Service
public class PersonService {
  
	@Autowired
	personRepository repository;
	
	public Person create(Person person){
		// save a single Customer
		Person newPerson = new person(person.getFirstName(), person.getLastName(), person.getRole());
		repository.save(newPerson);
		return newPerson;
	}

  public List<Person> findAll(boolean all){
    List<Person> persons;
    if (all == true) {  
      persons = repository.findAll();
    } else {
      persons = repository.findByIsDeleted(true);
    }
		return persons;
  }

  public Person findByCustomerId(UUID customerId){
    Person person = repository.findByCustomer(customerId);
    if (person.getDeleted() == false) {
      return person;
    } else throw(() -> new ResourceNotFoundException("Person with customer id " + customerId + " not found"));
  }
  
  public Person findById(UUID personId){
    Person person = repository.findById(personId);
    if (person.getDeleted() == false) {
      return person;
    } else throw(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }
  
  public String deletePerson(UUID personId) {
    return repository.findById(personId).map(person -> {
      person.setDeleted(true);
      return repository.save(person);
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }

  public String updatePerson(UUID personId, Person newPerson) {
    return repository.findById(personId).map(person -> {
      person.setFirstName(newPerson.getFirstName());
      person.setLastName(newPerson.getLastName());
      person.setRole(newPerson.getRole());
      person.setCustomer(newPerson.getCustomer());
      return repository.save(person);
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + uuid + " not found"));
  }	
}
