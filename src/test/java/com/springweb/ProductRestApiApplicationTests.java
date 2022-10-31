package com.springweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.springweb.entities.Product;

@SpringBootTest
class ProductRestApiApplicationTests {

	@Value("${product_rest_api.services.url}")
	private String baseURL;
	 
	
	@Test
	void testGetProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"4", Product.class); //url and Response type
		assertNotNull(product);
		assertEquals("Hp laptop", product.getName());
	}
	
	@Test
	void testCreateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setName("Samsung");
		product.setDescription("Awesome");
		product.setPrice(250000);
		Product newProduct = restTemplate.postForObject(baseURL, product, Product.class); //url, product to be added, responseType
		assertEquals("Samsung", newProduct.getName());
	}
	
	void testUpdateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"2", Product.class);
		product.setPrice(1400);
 		restTemplate.put("http://localhost:8080/productapi/products/", product);
	}

}
