package com.rvy.service;

import java.util.List;

import com.rvy.entity.Product;
import com.rvy.entity.ProductInventory;
import com.rvy.exception.ProductInventoryOperationException;
import com.rvy.exception.ProductOperationException;
import com.rvy.exception.ProductInventoryOperationException;

public interface ProductInventoryOperation {
	List<ProductInventory> getAllProductInventory() throws ProductInventoryOperationException;
	
	List<Product> getProductsByInvenroryId(List<Integer> idList) throws ProductInventoryOperationException;

	Product getProductByInventoryId(Integer Id) throws ProductInventoryOperationException;
	
	ProductInventory getProductInventoryById(Integer Id) throws ProductInventoryOperationException;
	
	ProductInventory addProductInventory(ProductInventory productInventory) throws ProductInventoryOperationException;
	
	ProductInventory updateProductInventory(ProductInventory productInventory) throws ProductInventoryOperationException;
	
	Integer deleteProductInventory(Integer id) throws ProductInventoryOperationException;

}
