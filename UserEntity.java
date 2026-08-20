package com.nt.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name="userservice")
@Data
public class UserEntity implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20,nullable=false)
	@NotBlank(message="First name is required")
	private String firstName;
	
	@Column(length=20,nullable=false)
	@NotBlank(message="Last name is required")
	private String lastName;
	
	@Column(length=50,nullable=false,unique=true)
	@NotBlank(message="Email is required")
	@Email(message="Enter a valid email")
	private String email;
	
	@Column(length=10,nullable=false)
	@NotBlank(message="Phone number is required")
	@Pattern(regexp="^[0-9]{10}$", message="Phone number must be exactly 10 digits")
	private String phone;
	
	@Column(length=80)
	private String address;
	
	@Column(nullable=false)
	@NotBlank(message="Password is required")
	private String password;
}
