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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class ContactControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ContactRepository repository;

	@Test
	public void givenEntityToSaveWhenPerformPostThenEntityIsCreated() throws Exception {
		mockMvc.perform(post("/contacts")
				.contentType(APPLICATION_JSON_UTF8)
				.content("[{\"idCustomer\": \"1\", \"info\":\"emailPost@example.com\"}]"))
				.andExpect(status().isCreated());

		Optional<List<Contact>> contactsOptional = repository.findByIdCustomer(1L);
		Assert.assertTrue(contactsOptional.isPresent());
		assertThat(contactsOptional.get()).extracting(Contact::getInfo).contains("emailPost@example.com");
	}

	@Test
	public void givenIdCustomerWhenPerformGetThenListContactEntityIsReturned() throws Exception {
		Contact contact = repository.save(new Contact(null,2L, "emailGet@example.com"));
		Assert.assertNotNull(contact);

		mockMvc.perform(get("/contacts/{idCustomer}", contact.getIdCustomer()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[?(@.idCustomer == '%s')]", String.valueOf(contact.getIdCustomer())).exists())
				.andExpect(jsonPath("$.[?(@.info == '%s')]", "emailGet@example.com").exists());
	}
}