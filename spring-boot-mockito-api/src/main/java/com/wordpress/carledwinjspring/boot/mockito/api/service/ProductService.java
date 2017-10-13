package com.wordpress.carledwinjspring.boot.mockito.api.service;

import com.wordpress.carledwinjspring.boot.mockito.api.dao.ProductDAO;
import com.wordpress.carledwinjspring.boot.mockito.api.exception.InsufficientProductsException;
import com.wordpress.carledwinjspring.boot.mockito.api.model.Product;

public class ProductService {

	private ProductDAO dao;
	
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}
	
	public boolean buy(Product product, int orderedQuantity) throws InsufficientProductsException{
		
		boolean transactionStatus = false;
		
		int avaliableQuantity = dao.getAvailableProducts(product);
		
		if(orderedQuantity > avaliableQuantity) {
			throw new InsufficientProductsException();
		}
		
		dao.orderProduct(product, orderedQuantity);
		
		transactionStatus = true;
		
		return transactionStatus;
	}
}
