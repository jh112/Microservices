package com.springboot.customer.api.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.springboot.customer.api.dao.CustRepo;
import com.springboot.customer.api.model.Customer;
import com.springboot.customer.api.service.CustomerService;
import com.springboot.customer.exception.CustomerServiceException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerUnitTests {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustRepo custRepo;

	@Test
	public void saveCustomerSuccessful() throws CustomerServiceException {
		customerService.saveCustomer(getCustomer());
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCuser("jaym");
		customer.setEmail("jaym@test.com");
		customer.setFirstName("Jay");
		customer.setLastName("Mevada");

		return customer;

	}
}
