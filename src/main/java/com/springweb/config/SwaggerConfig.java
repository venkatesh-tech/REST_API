package com.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.support.RequestHandledEvent;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()) // used to define api properties, used to replace default values
				.select().apis(RequestHandlerSelectors
				.basePackage("com.springweb.controller")) // With in this package
//				.paths(PathSelectors.regex("/products.*")) // only these urls are documented now.
				.build();
	}

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder().title("Product API")
				.description("Product CRUD Operations")
				.termsOfServiceUrl("Open Source")
				.license("Venkatesh License")
				.licenseUrl("Venkatesh-tech.com")
				.version("2.0")
				.build();
	}
}
