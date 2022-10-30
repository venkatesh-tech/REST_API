package com.springweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.entities.Product;
import com.springweb.repos.ProductRepository;

@RestController
public class ProductRestController {
	
	@Autowired
	ProductRepository repository;
	
	@GetMapping("/products/")
	public List<Product> getProducts(){
		return repository.findAll();
		
	}
}
