package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.Product;
import com.rvy.entity.ProductInventory;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class ProductInventorySteps extends AbstractSteps implements En {
	
	boolean notNull = false;
	@Autowired
	ProductInventory productInventory;
	
	
	public ProductInventorySteps() {
		
		Given("Admin wants to create a product inventory with the following attributes", (DataTable productInventoryDt) -> {
			testContext().reset();

			List<List<String>> productInventoryData = productInventoryDt.asLists(String.class);
			productInventory = populateProductInventory(productInventoryData.get(1));

		});

//		And("with the following details of product", (DataTable productDt) -> {
//			testContext().reset();
//
//			List<List<String>> productData = productDt.asLists(String.class);
//			product = populateProduct(productData.get(1));
//
//		});
//		
		When("admin saves the new product inventory {string}", (String testContext) -> {
			
			//      // AbstractSteps class makes the POST call and stores response in TestContext
			//      executePost(createTaxUrl);

			notNull = productInventory.getBatchcode().isEmpty()? false:true;
		});

		/**
		 * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
		 */
		Then("the save {string}", (String expectedResult) -> {
			assertTrue(notNull);
		});

	}


	private ProductInventory populateProductInventory(List<String> list) {
		ProductInventory productInventory = new ProductInventory();
		
		productInventory.setInventoryId(Integer.parseInt(list.get(0)));
		productInventory.setBuyingPrice(Double.parseDouble(list.get(1)));
		productInventory.setMinimumSellingPrice(Double.parseDouble(list.get(2)));
		productInventory.setBatchcode(list.get(3));
		productInventory.setBatchdate(null);
		productInventory.setQuantity(null);
		productInventory.setReorderLevel(null);
		productInventory.setStatus(null);
		productInventory.setStoreId(null);
		productInventory.setProduct(null);
		return productInventory;
		
	}

//	private Product populateProduct(List<String> list) {
//		Product product = new Product();
//		
//		product.setProductId(Integer.parseInt(list.get(0)));
//		product.setCategory(list.get(1));
//		product.setType(list.get(2));
//		product.setBrand(list.get(3));
//		product.setProductName(list.get(4));
//		product.setImage(null);
//		product.setProductDesc(null);
//		return product;
//	}

}
