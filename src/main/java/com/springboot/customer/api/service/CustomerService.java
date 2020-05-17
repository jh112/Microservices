package com.springboot.customer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.customer.api.dao.CustRepo;
import com.springboot.customer.api.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustRepo custRepo;

	public Customer saveCustomer(Customer customers) {
		custRepo.save(customers);
		return customers;

	}

	public Optional<Customer> getCustomer(int id) {
		return custRepo.findById(id);

	}

	public void deletCustomer(int id) {
		Customer customerOne = custRepo.getOne(id);
		custRepo.delete(customerOne);

	}

}
