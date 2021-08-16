package com.rvy.service;

import java.util.List;


import com.rvy.entity.Product;
import com.rvy.exception.ProductOperationException;

public interface ProductOperation {
	List<Product> getAllProducts() throws ProductOperationException;
	//List<Product> getProductsByType(String type) throws ProductOperationException;
	Product getDetailsById(Integer id) throws ProductOperationException;
	Product addProduct(Product product) throws ProductOperationException;
	Product updateProduct(Product product) throws ProductOperationException;
	Integer deleteProduct(Integer id) throws ProductOperationException;
	List<Product> getDetailsByAttribute(String brand, String type, String category) throws ProductOperationException;

}
