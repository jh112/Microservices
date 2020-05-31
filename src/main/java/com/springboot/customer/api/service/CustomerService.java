package com.springboot.customer.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.customer.api.dao.CustomerRepository;
import com.springboot.customer.api.model.Customer;
import com.springboot.customer.exception.CustomerServiceException;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository custRepo;

	public Customer saveCustomer(Customer customers) throws CustomerServiceException {
		try {

			logger.info("Customer Registration details updation");
			custRepo.save(customers);
			logger.info("Customer Registration details inserted or updated into database successfully");
		} catch (Exception e) {
			throw new CustomerServiceException(
					"Exception during customer registration because of username duplicate: " + customers.getCuser());
		}
		return customers;

	}

	public Customer getCustomer(String email) throws CustomerServiceException {
		Customer customerDetails = null;

		logger.info("Search customer");
		customerDetails = custRepo.findByEmailIgnoreCase(email);
		logger.info("Customer object: " + customerDetails);
		if (customerDetails == null) {
			throw new CustomerServiceException("Customer is not found: " + email);
		}
		logger.info("Customer is found in search criteria");
		return customerDetails;
	}

	public void deletCustomer(String email) throws CustomerServiceException {

		logger.info("Delete Cutomer");
		Customer customerOne = custRepo.findByEmailIgnoreCase(email);
		logger.info("CustomerOne object: " + customerOne);
		if (customerOne == null) {
			throw new CustomerServiceException("Customer is not found: " + email);
		}
		custRepo.delete(customerOne);
		logger.info("Delete Customer successfully");

	}

	

	public Customer getConfirmEmail(String customerDetails) {

		Customer confirmEmail = custRepo.findByEmailIgnoreCase(customerDetails);
		return confirmEmail;

	}

	public Customer getCuserDetails(String cuser) {

		Customer cuserDetails = custRepo.findByCuserIgnoreCase(cuser);
		return cuserDetails;

	}

}
