package com.springboot.customer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.customer.api.model.ConfirmationEmail;

public interface ConfirmationEmailRepository extends JpaRepository<ConfirmationEmail, String> {
	
	ConfirmationEmail findByConfirmationToken(String confirmationToken);
}
