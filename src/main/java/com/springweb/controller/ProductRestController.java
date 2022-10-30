package com.springweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.entities.Product;
import com.springweb.repos.ProductRepository;

@RestController
public class ProductRestController {
	
	@Autowired
	ProductRepository repository;

	// Finding all the products
//	@GetMapping("/products/")
	@RequestMapping(value="/products/", method = RequestMethod.GET)
	public List<Product> getProducts(){
		return repository.findAll();	
	}
	
	// Finding product by Id 
	@RequestMapping(value="/products/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") int id) {
		return repository.findById(id).get();	
	}
	
	//Creating a product
	@RequestMapping(value="/products/", method = RequestMethod.POST)
	public Product createProduct(Product product) {
		return repository.save(product);
	}
	
	//Updating the product
	@RequestMapping(value="/products/",method=RequestMethod.PUT)
	public Product updateProduct(Product product) {
		return repository.save(product);
	}
	
	@RequestMapping(value="/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id) {
		 repository.deleteById(id);
	}
}
