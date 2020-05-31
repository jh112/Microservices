package com.springboot.customer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.customer.api.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, String> {
	Customer findByEmailIgnoreCase(String email);
	Customer findByCuserIgnoreCase(String cuser);
}
