package com.rvy.controller;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.rvy.entity.Product;
import com.rvy.exception.ProductOperationException;
import com.rvy.service.ProductOperation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Api
@Slf4j
@RestController
@RequestMapping("/pms/v1")
public class ProductManagerController {
	@Autowired
	private ProductOperation productOperation;

	//http://localhost:8081/rvy/api/pms/v1/product/1
	@ApiOperation(value = "Product by id",
			consumes = "product master Id",
			produces = "Product object",
			response = Product.class,
			tags = "Find product by id",
			notes = "http://localhost:8081/rvy/api/pms/v1/product/{id}")
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) throws ResponseStatusException{
		try {
			Product product = productOperation.getDetailsById(id);
			log.info("inside findProductById method of controller");
			return new ResponseEntity<>(product,HttpStatus.OK);
		} catch (ProductOperationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
	}

	//http://localhost:8081/rvy/api/pms/v1/product/1
		@ApiOperation(value = "Product by Attribute set",
				consumes = "product master Id",
				produces = "Product object",
				response = Product.class,
				tags = "Find product by Attribute set",
				notes = "http://localhost:8081/rvy/api/pms/v1/product/{id}/{brand}/{type}/{category}")
		@GetMapping("/product/{brand}/{type}/{category}")
		public ResponseEntity<List<Product>> findProductByAttribute(@PathVariable("brand") String brand,@PathVariable("type") String type, @PathVariable("category") String category) throws ResponseStatusException{
			try {
				List<Product> products = productOperation.getDetailsByAttribute(brand,type,category);
//				log.info("inside findProductById method of controller");
				return new ResponseEntity<>(products,HttpStatus.OK);
			} catch (ProductOperationException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
			
		}
		
	//http://localhost:8081/rvy/api/pms/v1/products
	@ApiOperation(value = "List of all products",
			consumes = "",
			produces = "All the products",
			response = Product.class,
			tags = "Get All Products",
			notes = "http://localhost:8081/rvy/api/pms/v1/products")
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts()throws ResponseStatusException{

		try {
			List<Product> products = productOperation.getAllProducts();
			log.info("inside getAll products in controller");
			return new ResponseEntity<>(products,HttpStatus.OK);
		} catch (ProductOperationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
	}


	// http://localhost:8081/rvy/api/pms/v1/product
	@ApiOperation(value = "Add product to product master",
			consumes = "Product object",
			produces = "Product object",
			tags = "Add Product",
			notes = "http://localhost:8081/rvy/api/pms/v1/product")
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ResponseStatusException{
		Product prod = null;
		try {
			prod = productOperation.addProduct(product);
			return new ResponseEntity<>(prod,HttpStatus.OK);
		} catch (ProductOperationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

	// http://localhost:8081/rvy/api/pms/v1/product
	@ApiOperation(value = "Update order",
			consumes = "Product object",
			produces = "Product object",
			response =Product.class,
			tags = "Update Product Master",
			notes = "http://localhost:8081/rvy/api/pms/v1/product")
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ResponseStatusException{
		Product prod = null;
		try {
			prod = productOperation.updateProduct(product);
			return new ResponseEntity<>(prod,HttpStatus.OK);
		} catch (ProductOperationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	
	}

	// http://localhost:8081/rvy/api/pms/v1/product/1
	@ApiOperation(value = "Delete",
			consumes = "product Master Id",
			produces = "Product Object",
			response = Product.class,
			tags="Delete A Product",
			notes ="http://localhost:8081/rvy/api/pms/v1/product/{id}")
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Integer> deleteProduct(@PathVariable("id") Integer id){
		try {
			log.info("inside deleteProduct from Product master");
			Integer deletedProduct = productOperation.deleteProduct(id);
			return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
		} catch (ProductOperationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

}

