package com.example.demo.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//signals to Spring & Hibernate this is a entity class
// - hibernate handles this (ORM - Object Relational Mapper)
// - hibernate will create a table for animal in our database because it is a @Entity
// - hibernate will also pick up our validation annotations
// - this class models the subject of the business domain (domain driven development)
//   i.e. Animal is the domain
@Entity
@Table(name = "animal")
public class Animal {
	
	// when we specify @Entity, we must also specify an id
	@Id // this annotation is mandatory
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto assign ids
	private int id;
	
	@NotNull // validation, name must not be null
	private String name;
	
	@Min(0)
	@Max(150)
	private int age;
	
	@Column(name = "email", unique = true)
	private String email;
	
	// @Transient // transient fields are not mapped to the database
	private String type;
	
	public Animal() {
		
	}
	
	public Animal(int id, String name, int age, String email, String type) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, email, id, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return age == other.age && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}

}