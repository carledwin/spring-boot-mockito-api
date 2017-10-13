package com.wordpress.carledwinjspring.boot.mockito.api.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wordpress.carledwinjspring.boot.mockito.api.dao.ProductDAO;
import com.wordpress.carledwinjspring.boot.mockito.api.exception.InsufficientProductsException;
import com.wordpress.carledwinjspring.boot.mockito.api.model.Product;
import com.wordpress.carledwinjspring.boot.mockito.api.service.ProductService;

public class MockCreationAnnotationTest {

	@Mock
	private ProductDAO dao;
	
	@Mock
	private Product product;
	
	@Mock
	private ProductService service;
	
	@Before
	public void setupMock() {
		
		MockitoAnnotations.initMocks(this);
		service = new ProductService(this.dao);
	}
	
	@Test
	public void testMockCreationWithAnnotation() {
		
		assertNotNull(dao);
		assertNotNull(product);
		assertNotNull(service);
		assertNotNull(service.getDao());
	}
	
	@Test
	public void testBuy()throws InsufficientProductsException{
		
		when(dao.getAvailableProducts(product)).thenReturn(30);
		
		assertEquals(30, dao.getAvailableProducts(product));
		
		assertTrue("service.buy >> True ", service.buy(product, 5));
		
		verify(dao).orderProduct(product, 5);
		
	}
	
}
