package com.springboot.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springbooot.customer.api.dto.CustomerError;

@ControllerAdvice
public class CustomerException {

	@ExceptionHandler(CustomerServiceException.class)
	public ResponseEntity<CustomerError> mapException(CustomerServiceException cx) {
		CustomerError errors = new CustomerError(HttpStatus.CONFLICT.value(), cx.getMessage());
		return new ResponseEntity<CustomerError>(errors, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomerError> mapException(MethodArgumentNotValidException ex) {
		CustomerError errors = new CustomerError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<CustomerError>(errors, HttpStatus.BAD_REQUEST);

	}
}
