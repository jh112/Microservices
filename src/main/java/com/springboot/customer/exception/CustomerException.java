package com.springboot.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.springbooot.customer.api.dto.CustomerError;

@ControllerAdvice
public class CustomerException {

	public ResponseEntity<CustomerError> mapException(CustomerServiceException cx)
	{
		CustomerError errors= new CustomerError(HttpStatus.BAD_REQUEST.value(),cx.getMessage());
		return new ResponseEntity<CustomerError>(errors, HttpStatus.BAD_REQUEST);
		
	}
}
