package com.springweb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.springweb.entities.Product;
import com.springweb.repos.ProductRepository;

class ProductRestControllerMvcTest {

	@Autowired       // step 1 
	private MockMvc mockMvc;
	
	@MockBean       // step 2
	private ProductRepository repository;
	
	@Test
	void testFindAll() { //step 3
		Product product = new Product();
		product.setId(1);
		product.setName("one-plus");
		product.setDescription("It's Awesome");
		product.setPrice(1000);
		List<Product> products = Arrays.asList(product);
		when(repository.findAll()).thenReturn(products);
	
	}

}
