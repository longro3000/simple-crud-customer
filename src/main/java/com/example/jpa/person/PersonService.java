package com.example.jpa.person;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.example.jpa.exception.ResourceNotFoundException;

@Service
public class PersonService {
  
	@Autowired
	PersonRepository repository;
	
	public Person create(Person person){
		// save a single Customer
		Person newPerson = new Person(person.getFirstName(), person.getLastName(), person.getRole());
		repository.save(newPerson);
		return newPerson;
	}

  public List<Person> findAll(boolean all){
    List<Person> persons;
    if (all == true) {  
      persons = repository.findAll();
    } else {
      persons = repository.findByIsDeleted(false);
    }
		return persons;
  }

  public Person findByCustomerId(UUID customerId){
    return repository.findByCustomer(customerId).map(person -> {
    	if (person.getDeleted() == false) {
    		return person;
    	} else throw(new ResourceNotFoundException("Person with customer id " + customerId + " not found"));
    }).orElseThrow(() -> new ResourceNotFoundException("Person with customer id " + customerId + " not found"));
    
  }
  
  public Person findByUuid(UUID personId){
    return repository.findByUuid(personId).map(person -> {
    	 if (person.getDeleted() == false) {
    	      return person;
    	    } else throw(new ResourceNotFoundException("Person with customer id " + personId + " not found"));
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }
  
  public ResponseEntity<?> deletePerson(UUID personId) {
    return repository.findByUuid(personId).map(person -> {
      person.setDeleted(true);
      repository.save(person);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }

  public Person updatePerson(UUID personId, Person newPerson) {
    return repository.findByUuid(personId).map(person -> {
      person.setFirstName(newPerson.getFirstName());
      person.setLastName(newPerson.getLastName());
      person.setRole(newPerson.getRole());
      person.setCustomer(newPerson.getCustomer());
      return repository.save(person);
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }	
}
