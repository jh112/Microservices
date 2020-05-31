package com.springboot.customer.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.customer.api.endpoints.Endpoints;
import com.springboot.customer.api.model.ConfirmationEmail;
import com.springboot.customer.api.model.Customer;
import com.springboot.customer.api.service.CustomerEmailService;
import com.springboot.customer.api.service.CustomerService;
import com.springboot.customer.api.utils.Utils;
import com.springboot.customer.exception.CustomerServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Customer Service")
@RestController
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerEmailService customerEmailService;

	
	@ApiOperation(value = "Registration customer")
	@PostMapping(value = Endpoints.Customer_V1.registerOrUpdateCustomerEndpoint_v1, consumes = { "application/json" })
	public Customer registerCustomer(@RequestBody @Valid Customer customers) throws CustomerServiceException {
		logger.info("Process start of Registration");
		Customer customerEmail = customerService.getConfirmEmail(customers.getEmail());
	    Customer customerUserName = customerService.getCuserDetails(customers.getCuser());
		if (customerEmail != null) {
			if (customerEmail.getEmail().equals(customers.getEmail())) {
				throw new CustomerServiceException(
						"Exception during customer registration because email id duplicate: " + customers.getEmail());
			}
		} else if (customerUserName!= null) {
			if (customerUserName.getCuser().equals(customers.getCuser())) {
				throw new CustomerServiceException(
						"Exception during customer registration because of username duplicate: "
								+ customers.getCuser());
			}
		}
		customerEmailService.sendEmailtoCustomer(customers);
		customers.setPassword(Utils.getEncrpt(customers.getPassword()));
		return customerService.saveCustomer(customers);

	}

	@ApiOperation(value = "Search customer")
	@GetMapping(value = Endpoints.Customer_V1.getOrDeleteCustomerEndpoint_v1)
	public Customer getCustomers(@PathVariable String email) throws CustomerServiceException {
		logger.info("Process start of Search customer details");
		return customerService.getCustomer(email);

	}

	@ApiOperation(value = "Update OR Registration customer")
	@PutMapping(value = Endpoints.Customer_V1.registerOrUpdateCustomerEndpoint_v1, consumes = { "application/json" })
	public Customer updateCustomer(@RequestBody @Valid Customer customers) throws CustomerServiceException {
		logger.info("Process start of Update customer details");
		Customer customerEmail = customerService.getConfirmEmail(customers.getEmail());
		if (customerEmail != null) {
			customers.setId(customerEmail.getId());
			customers.setStatus(customerEmail.isStatus());

		} else {
			customerEmailService.sendEmailtoCustomer(customers);
		}
		customers.setPassword(Utils.getEncrpt(customers.getPassword()));
		return customerService.saveCustomer(customers);
	}

	@ApiOperation(value = "Delete customer")
	@DeleteMapping(value = Endpoints.Customer_V1.getOrDeleteCustomerEndpoint_v1)
	public void deleteCustomer(@PathVariable String email) throws CustomerServiceException {
		logger.info("Process start of delete customer details");
		customerService.deletCustomer(email);

	}

	@GetMapping(value = Endpoints.Customer_V1.confirmmEmail)
	public String confirmCustomerEmail(@RequestParam("token") String confirmationToken)
			throws CustomerServiceException {
		String display = "";
		ConfirmationEmail token = customerEmailService.getToken(confirmationToken);
		if (token != null) {
			Customer customer = customerService.getCustomer(token.getCustomer().getEmail());
			customer.setStatus(true);
			customerService.saveCustomer(customer);
			display = "Congratulations! Your account has been activated and email is verified!";
		} else {
			display = "The link is invalid or broken";
		}
		return display;

	}

}
