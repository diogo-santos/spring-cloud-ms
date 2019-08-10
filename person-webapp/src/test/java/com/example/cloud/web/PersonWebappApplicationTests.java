package com.example.cloud.web;

import com.example.cloud.web.client.PersonService;
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
public class PersonWebappApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PersonService personService;

	@Test
	public void whenGetThenShowWelcomePage() throws Exception {
		this.mockMvc.perform(get("/person"))
				.andExpect(content()
						.string(containsString("Welcome to the Person Page")));
	}

	@Test
	public void givenPersonWithNullFieldsWhenPostThenReturnFieldErrors() throws Exception {
		this.mockMvc.perform(post("/person"))
					.andExpect(model()
						.attributeHasFieldErrors("person","firstName", "lastName"));
	}

	@Test
	public void givenPersonWithEmptyFieldsWhenPostThenReturnFieldErrors() throws Exception {
		this.mockMvc.perform(post("/person")
							.param("firstName", "")
							.param("lastName", ""))
					.andExpect(model()
						.attributeHasFieldErrors("person","firstName", "lastName"));
	}

	@Test
	public void givenPersonWhenPostThenPersonIsSaved() throws Exception {
		this.mockMvc.perform(post("/person")
							.param("firstName", "test")
							.param("lastName", "test"))
					.andExpect(view()
						.name("redirect:/person"));
	}
}