package com.wordpress.carledwinjspring.boot.mockito.api.dao;

import com.wordpress.carledwinjspring.boot.mockito.api.model.Product;

public interface ProductDAO {
	
	int getAvailableProducts(Product product);
	
	int orderProduct(Product product, int orderedQuantity);
}
