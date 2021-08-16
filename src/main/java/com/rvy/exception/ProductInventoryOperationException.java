package com.rvy.exception;

public class ProductInventoryOperationException extends Exception {
	
	public ProductInventoryOperationException() {
		super("Unable ton retrieve product inventory");
		
	}
	
	public ProductInventoryOperationException(String message) {
		super(message);
		
	}

	public ProductInventoryOperationException(String message, Exception e) {
		// TODO Auto-generated constructor stub
		
		super(message,e);
	}
	
	

}
