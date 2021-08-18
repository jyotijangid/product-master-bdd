package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.rvy.entity.ProductInventory;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class FindProductInventorySteps extends AbstractSteps implements En{
	
	boolean notNull = false;
	@Autowired
	ProductInventory productInventory;
	List<ProductInventory> productInventoryList = new ArrayList<ProductInventory>();
	boolean found = false;
  public FindProductInventorySteps() {
	  
	  
	  
    Given("The following product inventory exists and Admin wants to find the product inventory by ID", (DataTable productInventoryDt) -> {
      testContext().reset();
      
      List<List<String>> productInventoryData = productInventoryDt.asLists(String.class);
      
      productInventoryList.add(populateProductInventory(productInventoryData.get(1)));
      productInventoryList.add(populateProductInventory(productInventoryData.get(2)));
      
    });

    When("admin wants to find the product inventory with ID {string}", (String id) -> {
    	Integer productInventoryId = Integer.parseInt(id);
    	for(ProductInventory c: productInventoryList) {
    		if(c.getInventoryId().equals(productInventoryId)) {
    			found =true;
    		}
    	}
    	
    });

    /**
     * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
     */
    Then("the product inventory is found", () -> {
//      
    	assertTrue(found);
//      }
    	
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
