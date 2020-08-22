package com.example.cloud.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerWebappApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;

	@Test
	public void whenGetThenShowWelcomePage() throws Exception {
		this.mockMvc.perform(get("/customer"))
				.andExpect(content()
						.string(containsString("No Customer has been created")));
	}

	@Test
	public void givenCustomerWithNullFieldsWhenPostThenReturnFieldErrors() throws Exception {
		this.mockMvc.perform(post("/customer"))
					.andExpect(model()
						.attributeHasFieldErrors("customer","firstName", "lastName"));
	}

	@Test
	public void givenCustomerWithEmptyFieldsWhenPostThenReturnFieldErrors() throws Exception {
		this.mockMvc.perform(post("/customer")
							.param("firstName", "")
							.param("lastName", ""))
					.andExpect(model()
						.attributeHasFieldErrors("customer","firstName", "lastName"));
	}

	@Test
	public void givenCustomerWhenPostThenCustomerIsSaved() throws Exception {
		this.mockMvc.perform(post("/customer")
							.param("firstName", "test")
							.param("lastName", "test"))
					.andExpect(view()
						.name("redirect:/customer"));
	}
}