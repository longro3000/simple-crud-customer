package com.example.jpa.person;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.example.jpa.customer.Customer;

@Entity(name = "person")
@Table(name = "person")
public class Person implements Serializable {
  @Id
  @GeneratedValue
  private Long id;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ColumnDefault("random_uuid()")
  @Column(name = "uuid", columnDefinition = "BINARY(16)")
  @NotNull
  private UUID uuid = UUID.randomUUID();

  @Column(name = "first_name", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String firstName;

  @Column(name = "last_name", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String lastName;

  @Column(name = "role", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String role;

  @Column(name = "is_deleted", columnDefinition = "boolean default false")
  @NotNull
  private boolean isDeleted = false;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", referencedColumnName = "uuid", nullable = true)
  private Customer customer;

  public Person() {
    super();
  }

  public Person(String firstName, String lastName, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }
  
  public UUID getUuid() {
	  return this.uuid;
  }
  
  public void setUuid(UUID uuid) {
	  this.uuid = uuid;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
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

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public boolean getIsDeleted() {
    return this.isDeleted;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
  
  public Customer getCustomer() {
    return this.customer;
  }
}