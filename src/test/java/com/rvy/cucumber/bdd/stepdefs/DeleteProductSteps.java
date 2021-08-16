package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.Product;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class DeleteProductSteps extends AbstractSteps implements En {
	
	@Autowired
	Product product;
	List<Product> productList = new ArrayList<Product>();
	boolean found = false;
	boolean deleted = false;
	public DeleteProductSteps() {

		Given("The following product exists and Admin wants to delete the product by ID", (DataTable productDt) -> {
			testContext().reset();
			List<List<String>> productData = productDt.asLists(String.class);
			productList.add(populateProduct(productData.get(1)));
			productList.add(populateProduct(productData.get(2)));
		});

		When("admin wants to delete the product with ID {string}", (String id) -> {
			Integer productId = Integer.parseInt(id);
			Product deleteprod = null;
			for(Product p: productList) {
				if(p.getProductId().equals(productId)) {
					deleteprod=p;
					break;
				}
			}
			if(deleteprod!=null) {
				productList.remove(deleteprod);
				for(Product p: productList) {
					if(p.getProductId().equals(productId)) {
						found = true;
						break;
					}
				}
				if(found!=true) {
					deleted = true;
				}
			}
			else {
				found = false;
				deleted = false;
			}
		});

		Then("the product is deleted", () -> {
			assertTrue(deleted);
		});

		Then("the product is not deleted", () -> {
			assertFalse(deleted); 
		});
	}


	private Product populateProduct(List<String> list) {
		Product product = new Product();
		
		product.setProductId(Integer.parseInt(list.get(0)));
		product.setCategory(list.get(1));
		product.setType(list.get(2));
		product.setBrand(list.get(3));
		product.setProductName(list.get(4));
		product.setImage(null);
		product.setProductDesc(null);
		return 	product;
	}
}


