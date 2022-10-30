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
	
//	@GetMapping("/products/")
	@RequestMapping(value="/products/", method = RequestMethod.GET)
	public List<Product> getProducts(){
		return repository.findAll();	
	}
	
	@RequestMapping(value="/products/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") int id) {
		return repository.findById(id).get();	
	}
}
