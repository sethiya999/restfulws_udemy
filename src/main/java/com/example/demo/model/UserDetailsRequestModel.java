package com.example.demo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDetailsRequestModel {

	@NotNull(message="Firstname cannot be empty")
	String firstname;
	
	@NotNull
	String lastname;
	
	@Email
	String email;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
