package com.example.jpa.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping()
	public Customer create(@RequestBody Customer customer){
		return customerService.create(customer);
	}
  
  @GetMapping()
	public List<Customer> findAll() {
    return customerService.findAll(true);
  }

  @GetMapping("/active")
	public List<Customer> findAllActive() {
    return customerService.findAll(true);
  }

  @GetMapping("/{customerId}")
	public Customer findByCustomerUuid(@PathVariable (value = "customerId") UUID customerId) {
    return customerService.findByCustomerUuid(customerId);
  }
  
  @DeleteMapping("/{customerId}")
	public ResponseEntity<?> delete(@PathVariable (value = "customerId") UUID customerId){
		return customerService.deleteCustomer(customerId);
  }
  
  @PutMapping("/{customerId}")
	public Customer update(@RequestBody Customer customer, @PathVariable (value = "customerId") UUID customerId){
		return customerService.updateCustomer(customerId, customer);
	}
}