package com.wordpress.carledwinjspring.boot.mockito.api.exception;

public class InsufficientProductsException extends Exception{

	private static final long serialVersionUID = 1l;
	
	private String message = null;
	
	public InsufficientProductsException() {};
	
	public InsufficientProductsException(String message) {
		super(message);
		this.message = message;
	}
	
	public InsufficientProductsException(Throwable cause) {
		super(cause);
	}
	
	@Override
	public String toString() {
		return message;
	}
}
