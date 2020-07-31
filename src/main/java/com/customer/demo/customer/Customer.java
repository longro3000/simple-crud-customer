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
  private boolean isActive = true;

  public Person() {
    super();
  }

  public Person(String name) {
    this.name = name
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public boolean getActive() {
    return this.isActive;
  }
}