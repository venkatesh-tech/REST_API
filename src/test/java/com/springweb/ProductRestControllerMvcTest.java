package com.springweb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.springweb.controller.ProductRestController;
import com.springweb.entities.Product;
import com.springweb.repos.ProductRepository;

//@RunWith
//@WebMvcTest
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = ProductRestController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ProductRestControllerMvcTest {
	
	private static final int PRODUCT_ID = 1; // step 6 
	private static final String PRODUCT_NAME = "one-plus";
	private static final String PRODUCT_DESCRIPTION = "Its Awesome";
	private static final int PRODUCT_PRICE = 1000;
	private static final String PRODUCT_URL = "/productapi/products/";
	private static final String CONTEXT_URL = "/productapi";

	@Autowired       // step 1 
	private MockMvc mockMvc;
	
	@MockBean       // step 2
	private ProductRepository repository;
	
	@Test
	void testFindAll() throws Exception { //step 3
		Product product = buildProduct();
		List<Product> products = Arrays.asList(product);
		when(repository.findAll()).thenReturn(products);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(get(PRODUCT_URL).contextPath(CONTEXT_URL)).andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(products))); // step 4 using ObjectWriter we can write objects into strings, into arrays.
																			// takes json array and converts it to json list
	}
	
	@Test
	void testCreateProduct() throws JsonProcessingException, Exception {
		Product product = buildProduct();
		when(repository.save(any())).thenReturn(product);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(post(PRODUCT_URL).contextPath(CONTEXT_URL)
				.contentType(MediaType.APPLICATION_JSON) // ContentType is used because data is sent
				.content(objectWriter.writeValueAsString(product))).andExpect(status().isOk()) // this objectWriter is used to send values
				.andExpect(content().json(objectWriter.writeValueAsString(product))); // this objectWriter is used to print values out
		
	}
	
	@Test
	void testUpdateProduct() throws JsonProcessingException, Exception {
		Product product = buildProduct();
		product.setPrice(1200);
		when(repository.save(any())).thenReturn(product);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(put(PRODUCT_URL).contextPath(CONTEXT_URL)
				.contentType(MediaType.APPLICATION_JSON) // ContentType is used because data is sent
				.content(objectWriter.writeValueAsString(product))).andExpect(status().isOk()) // this objectWriter is used to send values
		.andExpect(content().json(objectWriter.writeValueAsString(product))); // this objectWriter is used to print values out
		
	}
	
	@Test
	void testDeleteProduct() throws Exception {
		doNothing().when(repository).deleteById(PRODUCT_ID);
		mockMvc.perform(delete(PRODUCT_URL+PRODUCT_ID).contextPath(CONTEXT_URL)).andExpect(status().isOk());
	}

	private Product buildProduct() { // step 5
		Product product = new Product();
		product.setId(PRODUCT_ID);
		product.setName(PRODUCT_NAME);
		product.setDescription(PRODUCT_DESCRIPTION);
		product.setPrice(PRODUCT_PRICE);
		return product;
	}

}
