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
	
	@GetMapping("/bulkcreate")
	public String bulkCreate() {
		return customerService.bulkcreate();
	}
	
	@PostMapping("/create")
	public Customer create(@RequestBody Customer customer){
		return customerService.create(customer);
	}
	
	@GetMapping("/findall")
	public List<Customer> findAll(){
		return customerService.findAll();
	}
}