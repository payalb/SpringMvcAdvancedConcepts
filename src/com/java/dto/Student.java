package com.java.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Student {
	
	private int id;
	@NotEmpty
	@Size(min=5, max= 20)
	private String name;
	@NotEmpty
	@isValidPassword(message="You are entering a wrong password!")
	private String password; 
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

}
