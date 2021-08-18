package com.rvy.cucumber.bdd.stepdefs;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.ProductInventory;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class UpdateProductInventorySteps extends AbstractSteps implements En{

	
	boolean notNull = false;
	@Autowired
	ProductInventory productInventory;
	
	
	public UpdateProductInventorySteps() {
		Given("The following product inventory exists and Admin wants to update the batchcode", (DataTable productInventoryDt) -> {
		      testContext().reset();
		      
		      List<List<String>> productInventoryData = productInventoryDt.asLists(String.class);
		      productInventory = populateProductInventory(productInventoryData.get(1));  
		    });

		    When("admin updates the batchcode to {string}", (String newName) -> {    	
		    	productInventory.setBatchcode(newName);
		    });

		    Then("the update is done", () -> {
		    	assertEquals("AD",productInventory.getBatchcode());   	
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

}
