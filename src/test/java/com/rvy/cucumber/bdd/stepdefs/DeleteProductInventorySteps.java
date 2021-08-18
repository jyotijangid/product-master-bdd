package com.rvy.cucumber.bdd.stepdefs;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.ProductInventory;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class DeleteProductInventorySteps extends AbstractSteps implements En{

	@Autowired
	ProductInventory productInventory;
	List<ProductInventory> productInventoryList = new ArrayList<ProductInventory>();
	boolean found = false;
	boolean deleted = false;
	public DeleteProductInventorySteps() {

		Given("The following product inventory exists and Admin wants to delete the product inventory by ID", (DataTable productInventoryDt) -> {
			testContext().reset();
			List<List<String>> productInventoryData = productInventoryDt.asLists(String.class);
			productInventoryList.add(populateProductInventory(productInventoryData.get(1)));
			productInventoryList.add(populateProductInventory(productInventoryData.get(2)));
		});

		When("admin wants to delete the product inventory with ID {string}", (String id) -> {
			Integer productInventoryId = Integer.parseInt(id);
			ProductInventory deleteproductInventory = null;
			for(ProductInventory p: productInventoryList) {
				if(p.getInventoryId().equals(productInventoryId)) {
					deleteproductInventory=p;
					break;
				}
			}
			if(deleteproductInventory!=null) {
				productInventoryList.remove(deleteproductInventory);
				for(ProductInventory p: productInventoryList) {
					if(p.getInventoryId().equals(productInventoryId)) {
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

		Then("the product inventory is deleted", () -> {
			assertTrue(deleted);
		});

		Then("the product inventory is not deleted", () -> {
			assertFalse(deleted); 
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
