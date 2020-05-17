package com.springboot.customer.api.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.springbooot.customer.api.dto.CustomerDTO;
import com.springboot.customer.api.SpringBootCustomerApplication;
import com.springboot.customer.api.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCustomerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "test" })
@Import({

		H2DBConfig.class })
@AutoConfigureMockMvc

public class SpringBootCustomerApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private Gson gson;

	HttpHeaders headers = new HttpHeaders();

	@Test
	@Order(5)
	public void getCustomers() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Customer> response = testRestTemplate.exchange(createURLWithPort("/customers/3"), HttpMethod.GET,
				entity, Customer.class);
		org.junit.Assert.assertEquals("jaym3", response.getBody().getCuser());

	}
	
	@Test
	@Order(1)
    public void testCreateCustomerWithMockMVC() throws Exception {
		CustomerDTO customerDTO= new CustomerDTO();
		customerDTO.setId(6);
		customerDTO.setEmail("jaym@test.com");
		customerDTO.setCuser("jay");
        this.mockMvc.perform(post("/customers").content(gson.toJson(customerDTO)).contentType(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk())
          .andExpect(content().string(containsString("jay")));
    }
	
	@Test
	@Order(2)
    public void testUpdateCustomerWithMockMVC() throws Exception {
		CustomerDTO customerDTO= new CustomerDTO();
		customerDTO.setId(6);
		customerDTO.setEmail("jaym@test.com");
		customerDTO.setCuser("jay1");
        this.mockMvc.perform(put("/customers").content(gson.toJson(customerDTO)).contentType(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk())
          .andExpect(content().string(containsString("jay1")));
    }
	
	@Test
	@Order(3)
    public void testDeleteCustomerWithMockMVC() throws Exception {
	     
        this.mockMvc.perform(delete("/customers/2").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
       
    }
	
	
	@Test
	@Order(4)
	@Sql(scripts = "/commonScript/data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void testCreateRetrieveWithMockMVC() throws Exception {
     
        this.mockMvc.perform(get("/customers/2").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
          .andExpect(content().string(containsString("jaym2")));
    }
	

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
