package com.springboot.customer.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Table(name = "customers")
public class Customer {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Email cannot be null or empty")
	@Email
	private String email;

	@NotEmpty(message = "firstName cannot be null or empty")
	private String firstName;

	@NotEmpty(message = "lastName cannot be null or empty")
	private String lastName;

	@NotEmpty(message = "Customer Username cannot be null or empty")
	private String cuser;

	@NotEmpty(message = "password cannot be null or empty")
	private String password;

	private String cityName;
	private String stateName;
	private String countryName;

	@ApiModelProperty(position = 2, required = true, notes = "used to display first name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@ApiModelProperty(position = 3, required = true, notes = "used to display last name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ApiModelProperty(position = 1, required = true, hidden = true, notes = "used for customer id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ApiModelProperty(position = 4, required = true, notes = "used to display email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ApiModelProperty(position = 5, required = true, notes = "used to display customer user name")
	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	@ApiModelProperty(position = 7, required = false, notes = "used to display customer city name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@ApiModelProperty(position = 8, required = false, notes = "used to display customer state name")
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@ApiModelProperty(position = 9, required = false, notes = "used to display customer country name")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@ApiModelProperty(position = 6, required = true, notes = "used to display customer password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
