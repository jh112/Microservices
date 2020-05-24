package com.springboot.customer.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.customer.api.dao.CustRepo;
import com.springboot.customer.api.model.Customer;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustRepo custRepo;

	public Customer saveCustomer(Customer customers) {
		logger.info("Customer Registration details updation");
		custRepo.save(customers);
		return customers;

	}
	
	public Optional<Customer> getCustomer(int id) {
		logger.info("Search customer");
		return custRepo.findById(id);

	}
	
	public void deletCustomer(int id) {
		logger.info("Delete Cutomer");
		Customer customerOne = custRepo.getOne(id);
		custRepo.delete(customerOne);

	}

}
