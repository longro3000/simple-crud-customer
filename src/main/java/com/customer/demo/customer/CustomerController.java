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
	public List<Customer> findAll() {
    return customerService.findAll(true);
  }

  @GetMapping("/active")
	public List<Customer> findAll() {
    return customerService.findAll(true);
  }

  @GetMapping("/{customerId}")
	public Customer findByCustomerId(@PathVariable (value = "customerId") UUID customerId) {
    return customerService.findById(customerId);
  }
  
  @PostMapping("/")
	public void create(@RequestBody Customer customer){
		return customerService.create(customer);
  }
  
  @DeleteMapping("/{customerId}")
	public ResponseEntity<?> delete(@PathVariable (value = "customerId") UUID customerId){
		return customerService.deleteCustomer(customerId);
  }
  
  @PutMapping("/{customerId}")
	public customer update(@RequestBody customer customer, @PathVariable (value = "customerId") UUID customerId){
		return customerService.updatecustomer(customerId, customer);
	}
}