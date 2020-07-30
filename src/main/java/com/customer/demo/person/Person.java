package com.customer.demo.customer;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "person")
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @GeneratedValue
  @column(name = "uuid", columnDefinition = "BINARY(16)")
  private UUID uuid = UUID.randomUUID();

  @column(name = "first_name")
  @NotNull
  @Size(max = 250)
  private String firstName;

  @column(name = "last_name")
  @NotNull
  @Size(max = 250)
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY,
    mappedBy = "post")
  private Set<Customer> customers = new HashSet<>();

  public Person() {
    super();
  }

  public Person(String firstName, String lastName, ) {

  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer)
  }

  public HashSet<Customer> getCustomer() {
    return this.customers;
  }
}