package com.springboot.customer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.customer.api.model.Customer;

public interface CustRepo extends JpaRepository<Customer, Integer> {

}
