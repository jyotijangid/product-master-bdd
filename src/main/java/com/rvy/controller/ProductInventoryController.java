package com.rvy.controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.rvy.entity.Product;
import com.rvy.entity.ProductInventory;
import com.rvy.exception.ProductInventoryOperationException;
import com.rvy.exception.ProductOperationException;
import com.rvy.service.ProductInventoryOperation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Api
@Slf4j
@RestController
@RequestMapping("/pis/v1")
public class ProductInventoryController {
	
	@Autowired
	private ProductInventoryOperation productInventoryOperation;
	
	//http://localhost:8081/rvy/api/pis/v1/product/1
		@ApiOperation(value = "Product by id",
				consumes = "product Inventory Id",
				produces = "Product object",
				response = Product.class,
				tags = "Find product by inventory id",
				notes = "http://localhost:8081/rvy/api/pis/v1/product/{id}")
		@GetMapping("/product/{id}")
		public ResponseEntity<ProductInventory> findProductById(@PathVariable("id") Integer id) throws ResponseStatusException{
			try {
				ProductInventory product = productInventoryOperation.getProductInventoryById(id);
				log.info("inside findProductById method of controller");
				return new ResponseEntity<>(product,HttpStatus.OK);
			} catch (ProductInventoryOperationException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
			
		}

//		//http://localhost:8081/rvy/api/pis/v1/product/1
//			@ApiOperation(value = "Product by Attribute set",
//					consumes = "product master Id",
//					produces = "Product object",
//					response = Product.class,
//					tags = "Find product by Attribute set",
//					notes = "http://localhost:8081/rvy/api/pms/v1/product/{id}/{brand}/{type}/{category}")
//			@GetMapping("/product/{brand}/{type}/{category}")
//			public ResponseEntity<List<Product>> findProductByAttribute(@PathVariable("brand") String brand,@PathVariable("type") String type, @PathVariable("category") String category) throws ResponseStatusException{
//				try {
//					List<Product> products = productOperation.getDetailsByAttribute(brand,type,category);
////					log.info("inside findProductById method of controller");
//					return new ResponseEntity<>(products,HttpStatus.OK);
//				} catch (ProductOperationException e) {
//					throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
//				}
//				
//			}
			
		//http://localhost:8081/rvy/api/pis/v1/products
		@ApiOperation(value = "List of all products",
				consumes = "",
				produces = "All the products",
				response = Product.class,
				tags = "Get All Inventory Products",
				notes = "http://localhost:8081/rvy/api/pis/v1/products")
		@GetMapping("/products")
		public ResponseEntity<List<ProductInventory>> getAllProducts()throws ResponseStatusException{

			try {
				List<ProductInventory> products = productInventoryOperation.getAllProductInventory();
				log.info("inside getAll products in controller");
				return new ResponseEntity<>(products,HttpStatus.OK);
			} catch (ProductInventoryOperationException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
			
		}
		
		//http://localhost:8081/rvy/api/pis/v1/products
//				@ApiOperation(value = "List of all products",
//						consumes = "",
//						produces = "All the products",
//						response = Product.class,
//						tags = "Get Product from InventoryId",
//						notes = "http://localhost:8081/rvy/api/pis/v1/products/{id}")
//				@GetMapping("/productInventory/{id}")
//				public ResponseEntity<Product> getAllProductsByInventoryId(@PathVariable("id") Integer id)throws ResponseStatusException{
//
//					try {
//						ProductInventory product = productInventoryOperation.getProductInventoryById(id);
//						log.info("inside getAll products in controller");
//						return new ResponseEntity<>(product.getProduct(),HttpStatus.OK);
//					} catch (ProductInventoryOperationException e) {
//						throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
//					}
//					
//				}


		// http://localhost:8081/rvy/api/pis/v1/product
		@ApiOperation(value = "Add product to product Inventory",
				consumes = "Product object",
				produces = "Product object",
				tags = "Add Product to inventory",
				notes = "http://localhost:8081/rvy/api/pms/v1/product")
		@PostMapping("/product")
		public ResponseEntity<ProductInventory> addProduct(@RequestBody ProductInventory product) throws ResponseStatusException{
			ProductInventory prod = null;
			try {
				prod = productInventoryOperation.addProductInventory(product);
				return new ResponseEntity<>(prod,HttpStatus.OK);
			} catch (ProductInventoryOperationException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
		}

		// http://localhost:8081/rvy/api/pms/v1/product
		@ApiOperation(value = "Update order",
				consumes = "Product Inventory object",
				produces = "Product Inventory object",
				response =ProductInventory.class,
				tags = "Update Product Inventory",
				notes = "http://localhost:8081/rvy/api/pis/v1/product")
		@PutMapping("/product")
		public ResponseEntity<ProductInventory> updateProduct(@RequestBody ProductInventory product) throws ResponseStatusException{
			ProductInventory prod = null;
			try {
				prod = productInventoryOperation.updateProductInventory(product);
				return new ResponseEntity<>(prod,HttpStatus.OK);
			} catch (ProductInventoryOperationException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
		
		}
//
		// http://localhost:8081/rvy/api/pms/v1/product/1
		@ApiOperation(value = "Delete",
				consumes = "product Inventory Id",
				produces = "Product Object",
				response = ProductInventory.class,
				tags="Delete A Product Inventory",
				notes ="http://localhost:8081/rvy/api/pis/v1/product/{id}")
		@DeleteMapping("/product/{id}")
		public ResponseEntity<Integer> deleteProduct(@PathVariable("id") Integer id){
			try {
				log.info("inside deleteProduct from Product master");
				Integer deletedProduct = productInventoryOperation.deleteProductInventory(id);
				return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
			} catch (ProductInventoryOperationException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
//		}


}}
