package com.customer.demo.customer;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	private ArrayList<String[]> planList;
	private 
	@Autowired
	PlanRepository repository;
	
	public void readFromFile(String fileName) {
		try {
		      File file = new File(fileName);
		      Scanner fileReader = new Scanner(file);  
		      fileReader.nextLine();
		      while (fileReader.hasNextLine()) {
		        String data = fileReader.nextLine();
		        char sympol = '"';
		        if (data.length() > 1) {
			        if (data.indexOf(sympol) != -1) {
			        	String name = data.substring(0, data.lastIndexOf(sympol));
			        	String others = data.substring(data.lastIndexOf(sympol)+2, data.length());
			        	String[] otherData = others.split(",");
			        	String newName = name.replaceAll(""+sympol, "");
			        	ArrayList<String> newPlan = new ArrayList<String>();
			        	newPlan.add(newName);
			        	for (String s : otherData) {
			        		newPlan.add(s);
			        	}
			        	String[] stringPlan = newPlan.toArray(new String[0]);
			        	planList.add(stringPlan);
			        	
			        } else {
				        String[] plan = data.split(",");
				        planList.add(plan);
			        }
			      }
		      }
		      fileReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 
	}
	
	public String bulkcreate(){
		// save a list of Customers
		planList = new ArrayList<String[]>();
		readFromFile("src/main/java/crosskey/mortagePlan/plan/prospects.txt");
        for (String[] plan : planList) {
        	String customerName = plan[0];
        	double totalLoan = Double.parseDouble(plan[1]);
        	double yearInterest = Double.parseDouble(plan[2]);
        	int yearDuration = Integer.parseInt(plan[3]);
        	Plan newPlan = new Plan(customerName, totalLoan, yearInterest, yearDuration);
        	repository.save(newPlan);
        }
		
		return "new plans are created";
	}
	
	public Plan create(Plan plan){
		// save a single Customer
		Plan newPlan = new Plan(plan.getCustomerName(), plan.getTotalLoan(), plan.getYearInterest(), plan.getYearDuration());
		repository.save(newPlan);
		return newPlan;
	}
	
	public List<Plan> findAll(){
		List<Plan> plans = repository.findAll();
		return plans;
	}
	
}
