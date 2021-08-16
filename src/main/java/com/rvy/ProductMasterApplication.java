package com.rvy;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication(scanBasePackages = "com.rvy")
@EntityScan(basePackages = "com.rvy.entity")
@EnableJpaRepositories(basePackages = "com.rvy.dao")
@EnableOpenApi
public class ProductMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMasterApplication.class, args);
	}
	
	@Bean
	public Docket openApiPetStore() {
		return new Docket(DocumentationType.OAS_30)
				.groupName("open-api-product-management-service")
				.select()
				.paths(cmsPaths())
				.build();
	}
	
	
	


	private Predicate<String> cmsPaths() {
		return regex(".*/api/.*");
	}

}
