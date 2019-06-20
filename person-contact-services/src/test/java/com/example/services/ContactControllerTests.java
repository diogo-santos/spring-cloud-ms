package com.example.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ContactRepository repository;

	@Test
	public void givenEntityToSaveWhenPerformPostThenEntityIsCreated() throws Exception {
		mockMvc.perform(post("/contact")
				.contentType(APPLICATION_JSON_UTF8)
				.content("[{\"idPerson\": \"1\", \"info\":\"test\"}]"))
				.andExpect(status().isCreated());
	}

	@Test
	public void givenIdPersonWhenPerformGetThenListContactEntityIsReturned() throws Exception {
		Contact contact = repository.save(new Contact(null,2L, "email@example.com"));
		Assert.assertNotNull(contact);

		mockMvc.perform(get("/contact/{idPerson}", 2L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[?(@.idPerson == '%s')]", "2").exists())
				.andExpect(jsonPath("$.[?(@.info == '%s')]", "email@example.com").exists());
	}
}