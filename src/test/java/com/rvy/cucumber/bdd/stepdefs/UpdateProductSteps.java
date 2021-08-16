package com.rvy.cucumber.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.Product;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class UpdateProductSteps extends AbstractSteps implements En {
	
	boolean notNull = false;
	@Autowired
	Product product;
	
  public UpdateProductSteps() {
	  	  
    Given("The following product exists and Admin wants to update the productname", (DataTable productDt) -> {
      testContext().reset();
      
      List<List<String>> productData = productDt.asLists(String.class);
      product = populateProduct(productData.get(1));  
    });

    When("admin updates the product name to {string}", (String newName) -> {    	
    	product.setProductName(newName);
    });

    Then("the update is done", () -> {
    	assertEquals("Lenovo Ideapad 360",product.getProductName());   	
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