package com.springboot.customer.api.tests;


import static org.mockito.Mockito.lenient;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.springboot.customer.api.dao.CustomerRepository;
import com.springboot.customer.api.model.Customer;
import com.springboot.customer.api.service.CustomerService;
import com.springboot.customer.exception.CustomerServiceException;



@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceUnitTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository custRepo;

	@Test
	public void saveCustomerSuccessful() throws CustomerServiceException {
		 lenient().when(customerService.saveCustomer(getCustomer())).thenReturn(getCustomer());
		Assert.assertEquals(getCustomer().getFirstName(), "Jay");
		Assert.assertEquals(getCustomer().getLastName(), "Mevada");
		
	}
	
	
	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCuser("jaym");
		customer.setEmail("jaym@test.com");
		customer.setFirstName("Jay");
		customer.setLastName("Mevada");
		customer.setPassword("abc@123");
		return customer;

	}
	
}
