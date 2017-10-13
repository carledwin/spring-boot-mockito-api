package com.wordpress.carledwinjspring.boot.mockito.api.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.wordpress.carledwinjspring.boot.mockito.api.dao.ProductDAO;
import com.wordpress.carledwinjspring.boot.mockito.api.model.Product;

public class MockProductCreationTest {

	private ProductDAO dao;
	
	private Product product;
	
	@Before
	public void setupMock() {
		
		dao = mock(ProductDAO.class);
		
		product = mock(Product.class);
	}
	
	@Test
	public void testMockProductCreation() {
		
		assertNotNull(dao);
		assertNotNull(product);
	}
}
