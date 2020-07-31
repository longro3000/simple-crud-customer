package com.customer.demo.customer;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.customer.demo.customer.Customer;

@Entity(name = "person")
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @GeneratedValue
  @column(name = "uuid", columnDefinition = "BINARY(16)")
  private UUID uuid = UUID.randomUUID();

  @column(name = "first_name", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String firstName;

  @column(name = "last_name", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String lastName;

  @column(name = "role", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String role;

  @Column(name = "is_deleted", columnDefinition = "boolean default false")
  @NotNull
  private boolean isDeleted = false;

  @OneToMany(cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY,
    mappedBy = "post")
  private Set<Customer> customers = new HashSet<>();

  public Person() {
    super();
  }

  public Person(String firstName, String lastName, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
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

  public void setRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return this.role;
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer)
  }

  public HashSet<Customer> getCustomer() {
    return this.customers;
  }
}