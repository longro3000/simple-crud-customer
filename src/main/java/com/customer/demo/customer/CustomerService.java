package com.customer.demo.customer;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.example.jpa.exception.ResourceNotFoundException;

@Service
public class CustomerService {
  
	@Autowired
	CustomerRepository repository;
	
	public Customer create(Customer customer){
		Customer newCustomer = new Customer(customer.getName());
		repository.save(newCustomer);
		return newCustomer;
	}

  public List<Customer> findAll(boolean isActive){
    List<Customer> customers;
    if (isActive == false) {  
      customers = repository.findAll();
    } else {
      customers = repository.findByIsActiveTrue();
    }
		return customers;
  }

  public Customer findByCustomerId(UUID customerId){
    Customer customer = repository.findById(customerId).map(customer -> {
      return customer;
    }).orElseThrow(() -> new ResourceNotFoundException("customer with customer id " + customerId + " not found"));
  }
  
  public String deleteCustomer(UUID customerId) {
    return repository.findById(customerId).map(customer -> {
      repository.delete(customer);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("customer with id " + customerId + " not found"));
  }

  public String updateCustomer(UUID customerId, Customer newCustomer) {
    return repository.findById(CustomerId).map(customer -> {
      customer.setName(newCustomer.getName());
      return repository.save(customer);
    }).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + uuid + " not found"));
  }	
}
