package com.nt.dto;

public class UserCreatedEvent {
	private Integer id;
	private String firstName;
	private String email;
	
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}
	
	@Override
	public String toString() {
		return "UserCreatedEvent[id="+id+ ", firstName="+ firstName+ ", email="+email+"]";
	}
}
