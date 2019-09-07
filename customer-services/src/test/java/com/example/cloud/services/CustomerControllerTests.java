package com.example.cloud.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CustomerRepository repository;

	@Test
	public void givenEntityToSaveWhenPerformPostThenEntityIsCreated() throws Exception {
		mockMvc.perform(post("/customers")
						.contentType(APPLICATION_JSON_UTF8)
						.content("{\"firstName\": \"CustomerNamePost\", \"lastName\":\"CustomerLastNamePost\"}"))
				.andExpect(status().isCreated());

		List<Customer> customers = repository.findAll();
		assertThat(customers).extracting(Customer::getFirstName).contains("CustomerNamePost");
		assertThat(customers).extracting(Customer::getLastName).contains("CustomerLastNamePost");
	}

	@Test
	public void givenIdCustomerWhenPerformGetThenListContactEntityIsReturned() throws Exception {
		Customer customer = repository.save(new Customer(null, "CustomerNameGet", "CustomerLastNameGet"));
		Assert.assertNotNull(customer);

		mockMvc.perform(get("/customers/{id}", customer.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[?(@.firstName == '%s')]", "CustomerNameGet").exists())
				.andExpect(jsonPath("$.[?(@.lastName == '%s')]", "CustomerLastNameGet").exists());
	}
}