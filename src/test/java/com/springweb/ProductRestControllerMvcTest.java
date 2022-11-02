package com.springweb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;

import com.springweb.controller.ProductRestController;
import com.springweb.entities.Product;
import com.springweb.repos.ProductRepository;

//@RunWith
//@WebMvcTest
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = ProductRestController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ProductRestControllerMvcTest {
	@Autowired       // step 1 
	private MockMvc mockMvc;
	
	@MockBean       // step 2
	private ProductRepository repository;
	
	@Test
	void testFindAll() throws Exception { //step 3
		Product product = new Product();
		product.setId(1);
		product.setName("one-plus");
		product.setDescription("It's Awesome");
		product.setPrice(1000);
		List<Product> products = Arrays.asList(product);
		when(repository.findAll()).thenReturn(products);
		
		mockMvc.perform(get("/productapi/products/").contextPath("/productapi")).andExpect(status().isOk()); // step 4
	
	}

}
