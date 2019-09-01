package com.example.cloud.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerBusinessServicesApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerBusinessProcess businessProcess;

	@Test
	public void whenPerformGetThenListIsReturned() throws Exception {
		mockMvc.perform(get("/customers"))
				.andExpect(status().isOk());
	}

	@Test
	public void givenIdCustomerWhenPerformGetThenListIsReturned() throws Exception {
		mockMvc.perform(get("/customers/{idCustomer}", 1))
				.andExpect(status().isOk());
	}

	@Test
	public void givenCustomerWithContactsWhenPerformPostThenCustomerAndContactsAreCreated() throws Exception {
		mockMvc.perform(post("/customers")
						.contentType(APPLICATION_JSON_UTF8)
						.content("{\"firstName\": \"GivenName\", \"lastName\":\"LastName\", \"contacts\":[{\"info\":\"email@example.com\"}]}"))
				.andExpect(status().isOk());
	}

	@Test
	public void givenCustomerWithoutContactsWhenPerformPostThenCustomerIsCreated() throws Exception {
		mockMvc.perform(post("/customers")
				.contentType(APPLICATION_JSON_UTF8)
				.content("{\"firstName\": \"GivenName\", \"lastName\":\"LastName\"}"))
				.andExpect(status().isOk());
	}
}