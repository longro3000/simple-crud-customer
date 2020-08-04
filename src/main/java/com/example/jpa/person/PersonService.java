package com.example.jpa.person;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.example.jpa.customer.CustomerRepository;
import com.example.jpa.exception.ResourceNotFoundException;

@Service
public class PersonService {
  
	@Autowired
  PersonRepository repository;

  @Autowired
  CustomerRepository customerRepository;
	
	public Person create(Person person){
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
  
  public Person updateCustomerId(UUID personId, UUID customerId) {  
	  return customerRepository.findByUuid(customerId).map(customer -> {
		  return repository.findByUuid(personId).map(person -> {
			  person.setCustomer(customer);
			  return repository.save(person);
		  }).orElseThrow(() -> new ResourceNotFoundException("person with id " + personId + " not found"));
	  }).orElseThrow(() -> new ResourceNotFoundException("customer with id " + customerId + " not found"));
  }
  
  public List<Person> findByCustomerUuid(UUID customerId) {
	  return repository.findByCustomerUuidAndIsDeletedFalse(customerId);
  }
  
  public Person findByUuid(UUID personId){
    return repository.findByUuid(personId).map(person -> {
    	 if (person.getIsDeleted() == false) {
    	      return person;
    	    } else throw(new ResourceNotFoundException("Person with customer id " + personId + " not found"));
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }
  
  public ResponseEntity<?> deletePerson(UUID personId) {
    return repository.findByUuid(personId).map(person -> {
      person.setIsDeleted(true);
      repository.save(person);
      return ResponseEntity.noContent().build();
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }

  public Person updatePerson(UUID personId, Person newPerson) {
    return repository.findByUuid(personId).map(person -> {
      person.setFirstName(newPerson.getFirstName());
      person.setLastName(newPerson.getLastName());
      person.setRole(newPerson.getRole());
      return repository.save(person);
    }).orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
  }	
}
