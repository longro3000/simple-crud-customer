package com.customer.demo.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/bulkcreate")
	public String bulkCreate() {
		return personService.bulkcreate();
	}
	
	@PostMapping("/create")
	public Person create(@RequestBody person person){
		return personService.create(person);
	}
	
	@GetMapping("/findall")
	public List<person> findAll(){
		return personService.findAll();
  }
  
  @Mapping("/create")
	public void create(@RequestBody person person){
		return personService.create(person);
	}
}