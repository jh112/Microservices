package com.springboot.customer.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.customer.api.dao.CustRepo;
import com.springboot.customer.api.model.Customer;
import com.springboot.customer.exception.CustomerServiceException;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustRepo custRepo;

	public Customer saveCustomer(Customer customers) throws CustomerServiceException {
		try {
			logger.info("Customer Registration details updation");
			custRepo.save(customers);
		} catch (Exception ex) {
			throw new CustomerServiceException("Exception duering customer registration: " + customers);
		}

		return customers;

	}

	public Optional<Customer> getCustomer(int id) throws CustomerServiceException {
		Optional<Customer> customerDetails = null;
		try {
			logger.info("Search customer");
			customerDetails = custRepo.findById(id);
		} catch (Exception ex) {
			throw new CustomerServiceException("Exception during search customer:" + id);
		}

		return customerDetails;

	}

	public void deletCustomer(int id) throws CustomerServiceException {
		try {
			logger.info("Delete Cutomer");
			Customer customerOne = custRepo.getOne(id);
			custRepo.delete(customerOne);
		} catch (Exception ex) {
			throw new CustomerServiceException("Exception during delete customer:" + id);
		}
	}

}
