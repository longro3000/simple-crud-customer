package com.example.jpa.person;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
 
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
	
  @Autowired
  private PersonService personService;

  @PostMapping()
  public Person create(@RequestBody Person person){
    return personService.create(person);
  }
  
  @GetMapping()
  public List<Person> findAllUnDeleted() {
    return personService.findAll(false);
  }
  
  @GetMapping("/all")
  public List<Person> findAll() {
	  return personService.findAll(true);
  }
  
  @GetMapping("/customer/{customerId}")
	public List<Person> findByCustomerUuid(@PathVariable (value = "customerId") UUID customerId) {
	  return personService.findByCustomerUuid(customerId);
  }


  @GetMapping("/{personId}")
  public Person findByPersonId(@PathVariable (value = "personId") UUID personId) {
	  return personService.findByUuid(personId);
  }
  
  @PutMapping("/{personId}/customer/{customerId}")
  public Person updateCustomerId(
    @PathVariable (value = "personId") UUID personId,
    @PathVariable (value = "customerId") UUID customerId
  ) {
    return personService.updateCustomerId(personId, customerId);
  }
  
  @DeleteMapping("/{personId}")
  public ResponseEntity<?> delete(@PathVariable (value = "personId") UUID personId) {
	  return personService.deletePerson(personId);
  }
  
  @PutMapping("/{personId}")
  public Person update(@RequestBody Person person, @PathVariable (value = "personId") UUID personId) {
	  return personService.updatePerson(personId, person);
  }
}