package com.example.jpa.customer;

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

  public Customer findByCustomerUuid(UUID customerId){
    return repository.findByUuid(customerId).map(customer -> {
      return customer;
    }).orElseThrow(() -> new ResourceNotFoundException("customer with customer id " + customerId + " not found"));
  }
  
  public ResponseEntity<?> deleteCustomer(UUID customerId) {
    return repository.findByUuid(customerId).map(customer -> {
      repository.delete(customer);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("customer with id " + customerId + " not found"));
  }

  public Customer updateCustomer(UUID customerId, Customer newCustomer) {
    return repository.findByUuid(customerId).map(customer -> {
      customer.setName(newCustomer.getName());
      return repository.save(customer);
    }).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " not found"));
  }	
}
