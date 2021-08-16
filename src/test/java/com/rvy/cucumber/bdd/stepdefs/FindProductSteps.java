package com.rvy.cucumber.bdd.stepdefs;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.Product;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

/**
 * Step Definition Class for Product.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class FindProductSteps extends AbstractSteps implements En {
	
	boolean notNull = false;
	@Autowired
	Product product;
	List<Product> productList = new ArrayList<Product>();
	boolean found = false;
  public FindProductSteps() {
	  
	  
	  
    Given("The following product exists and Admin wants to find the product by ID", (DataTable productDt) -> {
      testContext().reset();
      
      List<List<String>> productData = productDt.asLists(String.class);
      
      productList.add(populateProduct(productData.get(1)));
      productList.add(populateProduct(productData.get(2)));
      
    });

    When("admin wants to find the product with ID {string}", (String id) -> {
    	Integer productId = Integer.parseInt(id);
    	for(Product c: productList) {
    		if(c.getProductId().equals(productId)) {
    			found =true;
    		}
    	}
    	
    });

    /**
     * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
     */
    Then("the product is found", () -> {
//      
    	assertTrue(found);
//      }
    	
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

