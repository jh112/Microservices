package com.springboot.customer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.*" })

public class SpringBootCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCustomerApplication.class, args);
	}

}
