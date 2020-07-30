package com.customer.demo.customer;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.customer.demo.person.Person;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @GeneratedValue
  @column(name = "uuid", columnDefinition = "BINARY(16)")
  private UUID uuid = UUID.randomUUID();

  @column(name = "name", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String name;

  @Column(name = "is_active", columnDefinition = "boolean default true")
  @NotNull
  private boolean isActive;

  @OneToMany(fetch = FetchType.LAZY)
  private Person person = new Person();

  public Person() {
    super();
  }

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
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