package com.springboot.customer.api.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.springboot.customer.api.model.Customer;

public class CustomerIntegrationTests extends SpringBootCustomerApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	@Sql(scripts = "/commonScript/cleanTable.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = "/commonScript/data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void getCustomers() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Customer> response = testRestTemplate.exchange(createURLWithPort("/customers/1"), HttpMethod.GET,
				entity, Customer.class);
		org.junit.Assert.assertEquals("test1", response.getBody().getFirstName());

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
