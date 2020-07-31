package com.customer.demo.person;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
 
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/")
	public Person create(@RequestBody person person){
		return personService.create(person);
  }
  
  @GetMapping("/")
	public List<Person> findAll() {
    return personService.findAll(false);
  }

  @GetMapping("/customer/{customerId}")
	public Person findAll(@PathVariable (value = "customerId") UUID customerId) {
    return personService.findByCustomer(customerId);
  }
	
	@GetMapping("/all")
	public List<Person> findAll() {
    return personService.findAll(true);
  }

  @GetMapping("/{personId}")
	public Person findByPersonId(@PathVariable (value = "personId") UUID personId) {
    return personService.findById(personId);
  }
  
  @PostMapping("/")
	public void create(@RequestBody person person){
		return personService.create(person);
  }
  
  @DeleteMapping("/{personId}")
	public ResponseEntity<?> delete(@PathVariable (value = "personId") UUID personId){
		return personService.deletePerson(personId);
  }
  
  @PutMapping("/{personId}")
	public Person update(@RequestBody person person, @PathVariable (value = "personId") UUID personId){
		return personService.updatePerson(personId, person);
	}
}