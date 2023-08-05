package com.example.day1.model.response;

public class UserResponse {

	private int id;
	private String firstName;
	private String lastName;

	public UserResponse() {
	}

	public UserResponse(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}
