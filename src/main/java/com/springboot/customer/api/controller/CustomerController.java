package com.springboot.customer.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.customer.api.model.Customer;
import com.springboot.customer.api.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Customer Service")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
    @ApiOperation(value="Save customer")
	@PostMapping(value = "/customers", consumes = { "application/json" })
	public Customer customers1(@RequestBody Customer customers) {
		return customerService.saveCustomer(customers);

	}
    @ApiOperation(value="Search customer")
	@GetMapping(value = "/customers/{id}")
	public Optional<Customer> customers(@PathVariable int id) {

		return customerService.getCustomer(id);

	}
    @ApiOperation(value="Update customer")
	@PutMapping(value = "/customers", consumes = { "application/json" })
	public Customer customers2(@RequestBody Customer customers) {

		return customerService.saveCustomer(customers);
	}
    @ApiOperation(value="Delete customer")
	@DeleteMapping(value = "/customers/{id}")
	public void customers4(@PathVariable int id) {
		customerService.deletCustomer(id);

	}

}
