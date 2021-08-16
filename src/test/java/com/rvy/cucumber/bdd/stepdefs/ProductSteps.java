package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.Product;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class ProductSteps extends AbstractSteps implements En {
	
	boolean notNull = false;
	@Autowired
	Product product;
	
	public ProductSteps() {
		
		Given("Admin wants to create a product with the following attributes", (DataTable taxDt) -> {
			testContext().reset();

			List<List<String>> productData = taxDt.asLists(String.class);
			product = populateProduct(productData.get(1));

		});

		When("admin saves the new product {string}", (String testContext) -> {
			
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createTaxUrl);

			notNull = product.getProductName().isEmpty()? false:true;
		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the save {string}", (String expectedResult) -> {
			assertTrue(notNull);
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
