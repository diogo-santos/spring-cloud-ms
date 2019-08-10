package com.example.cloud.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PersonRepository personRepo;

	@Test
	public void givenEntityToSaveWhenPerformPostThenEntityIsCreated() throws Exception {
		mockMvc.perform(post("/person")
						.contentType(APPLICATION_JSON_UTF8)
						.content("{\"firstName\": \"GivenName\", \"lastName\":\"LastName\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void givenIdPersonWhenPerformGetThenListContactEntityIsReturned() throws Exception {
		Person person = personRepo.save(new Person(null, "personName", "lastPersonName"));
		Assert.assertNotNull(person);

		mockMvc.perform(get("/person/{id}", person.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[?(@.firstName == '%s')]", "personName").exists())
				.andExpect(jsonPath("$.[?(@.lastName == '%s')]", "lastPersonName").exists());
	}
}