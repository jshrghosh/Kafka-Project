package com.nt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {
	@NotBlank(message="First name is required")
	private String firstName;
	
	@NotBlank(message="Last name is required")
	private String lastName;
	
	@NotBlank(message="Email is required")
	@Email
	private String email;
	
	@NotBlank(message="Phone number is required")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
	@Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
	private String phone;
	
	private String address;
	
	@NotBlank(message="Password is required")
	@Size(min = 8, max = 8, message = "Password must be exactly of 8 characters")
	private String password;

	public UserRequest() {}

	public UserRequest(String firstName, String lastName, String email,String phone, String address, String password) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email=email;
	this.phone=phone;
	this.address=address;
	this.password=password;
	}

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getemail() {
		return email;
	}
	
	public String getphone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPassword() {
		return password;
	}
	
}
