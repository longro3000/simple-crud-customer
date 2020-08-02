package com.example.jpa.customer;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.example.jpa.person.Person;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue
  private Long id;

  @GeneratedValue
  @Column(name = "uuid", columnDefinition = "BINARY(16)")
  @ColumnDefault("random_uuid()")
  private UUID uuid = UUID.randomUUID();

  @Column(name = "name", columnDefinition = "varchar(255)")
  @NotNull
  @Size(max = 255)
  private String name;

  @Column(name = "is_active", columnDefinition = "boolean default true")
  @NotNull
  private boolean isActive = true;

  public Customer() {
    super();
  }

  public Customer(String name) {
    this.name = name;
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