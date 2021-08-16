package com.rvy.exception;

import org.springframework.web.server.ResponseStatusException;

public class ProductOperationException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ProductOperationException() {
		super("Unable to retreive product");
	}

	public ProductOperationException(String message) {
		super(message);	
	}


	public ProductOperationException(String message,Throwable e) {
		super(message,e);
	}
	
	

}
