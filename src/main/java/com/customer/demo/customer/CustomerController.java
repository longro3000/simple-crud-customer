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
	private PlanService planService;
	
	@GetMapping("/bulkcreate")
	public String bulkCreate() {
		return planService.bulkcreate();
	}
	
	@PostMapping("/create")
	public Plan create(@RequestBody Plan plan){
		return planService.create(plan);
	}
	
	@GetMapping("/findall")
	public List<Plan> findAll(){
		return planService.findAll();
	}
}