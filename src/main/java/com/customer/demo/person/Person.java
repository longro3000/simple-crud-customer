package com.customer.demo.customer;

import java.text.DecimalFormat;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.customer.demo.customer.Customer;

@Entity(name = "person")
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @GeneratedValue
  @Column(name = "uuid", columnDefinition = "BINARY(16)")
  private UUID uuid = UUID.randomUUID();

  @Column(name = "first_name", columnDefinition = "varchar(255)")
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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id", referencedColumnName = "uuid")
  private Customer customer;

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

  public boolean setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public boolean getDeleted(boolean isDeleted) {
    return this.isDeleted;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer
  }

  public Customer getCustomer() {
    return this.customer;
  }
}