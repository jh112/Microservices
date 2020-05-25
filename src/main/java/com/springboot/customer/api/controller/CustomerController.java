package com.springboot.customer.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.customer.api.endpoints.Endpoints;
import com.springboot.customer.api.model.Customer;
import com.springboot.customer.api.service.CustomerService;
import com.springboot.customer.exception.CustomerServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Customer Service")
@RestController
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Save customer")
	@PostMapping(value = Endpoints.Customer_V1.registerOrUpdateCustomerEndpoint_v1, consumes = { "application/json" })
	public Customer saveCustomer(@RequestBody Customer customers) throws CustomerServiceException {
		logger.info("Process start of Registration");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(customers.getPassword());
		customers.setPassword(hashedPassword);
		return customerService.saveCustomer(customers);

	}

	@ApiOperation(value = "Search customer")
	@GetMapping(value = Endpoints.Customer_V1.getOrDeleteCustomerEndpoint_v1)
	public Optional<Customer> getCustomers(@PathVariable int id) throws CustomerServiceException {
		logger.info("Process start of Search customer details");
		return customerService.getCustomer(id);

	}

	@ApiOperation(value = "Update customer")
	@PutMapping(value = Endpoints.Customer_V1.registerOrUpdateCustomerEndpoint_v1, consumes = { "application/json" })
	public Customer updateCustomer(@RequestBody Customer customers) throws CustomerServiceException {
		logger.info("Process start of Update customer details");
		return customerService.saveCustomer(customers);
	}

	@ApiOperation(value = "Delete customer")
	@DeleteMapping(value = Endpoints.Customer_V1.getOrDeleteCustomerEndpoint_v1)
	public void deleteCustomer(@PathVariable int id) throws CustomerServiceException {
		logger.info("Process start of delete customer details");
		customerService.deletCustomer(id);

	}

}
