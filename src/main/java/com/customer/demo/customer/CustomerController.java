package com.customer.demo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public Customer create(@RequestBody Customer customer){
		return customerService.create(customer);
  }
  
  @GetMapping("/")
	public List<Person> findAll() {
    return personService.findAll(true);
  }

  @GetMapping("/active")
	public List<Person> findAll() {
    return personService.findAll(true);
  }
	
  @GetMapping("/customer/{customerId}")
	public Person findAll(@PathVariable (value = "customerId") UUID customerId) {
    return personService.findByCustomer(customerId);
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