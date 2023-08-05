package com.example.day1.model.request;

import java.util.Objects;

public class UserRequest{
	private String firstName;
	private String lastName;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserRequest that = (UserRequest) o;
		return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}
}
