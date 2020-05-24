package com.springbooot.customer.api.dto;




public class CustomerDTO {
	
private int id;
private String email;
private String cuser;
private String firstName;
private String lastName;

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCuser() {
	return cuser;
}
public void setCuser(String cuser) {
	this.cuser = cuser;
}


}
