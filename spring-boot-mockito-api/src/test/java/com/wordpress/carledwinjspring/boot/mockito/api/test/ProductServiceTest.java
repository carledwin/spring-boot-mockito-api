package com.wordpress.carledwinjspring.boot.mockito.api.test;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.wordpress.carledwinjspring.boot.mockito.api.dao.ProductDAO;
import com.wordpress.carledwinjspring.boot.mockito.api.exception.InsufficientProductsException;
import com.wordpress.carledwinjspring.boot.mockito.api.model.Product;
import com.wordpress.carledwinjspring.boot.mockito.api.service.ProductService;

public class ProductServiceTest {

	private ProductService service;
	
	private ProductDAO dao;
	
	private Product product;
	
	private int purchaseQuantity = 15;
	
	@Before
	public void setupMock() {
		
		service = new ProductService();
		
		product = mock(Product.class);
		
		dao = mock(ProductDAO.class);
		
		service.setDao(dao);
	}
	
	@Test
	public void testBuy() throws InsufficientProductsException{
		
		int avaliableQuantity = 30;
		
		System.out.println("Stubbing getAvaliableProducts(product)" + avaliableQuantity);
		
		when(dao.getAvailableProducts(product)).thenReturn(avaliableQuantity);
		
		System.out.println("Calling ProductService.buy(product, " + purchaseQuantity +")");
		
		service.buy(product, purchaseQuantity);
		
		System.out.println("Verifying ProductDAO(product, " + purchaseQuantity + ") is called");
		
		verify(dao).orderProduct(product, purchaseQuantity);
		
		System.out.println("Verify getAvailableProducts(product) is colled at least once");
		
		verify(dao, atLeastOnce()).getAvailableProducts(product);
		
		System.out.println("Verifying orde of method calls on ProductDAO: First call getAvailableProducts(product");
		
		InOrder order = inOrder(dao);
		
		order.verify(dao).getAvailableProducts(product);
		order.verify(dao).orderProduct(product, purchaseQuantity);
	}
	
	@Test(expected = InsufficientProductsException.class)
	public void purchaseWithInsufficientAvailableQuantity() throws InsufficientProductsException{
		int availableQuantity = 3;
		
		System.out.println("Stubbing getAvailableProducts(product) to return " + availableQuantity);
		
		when(dao.getAvailableProducts(product)).thenReturn(availableQuantity);
		
		try {
			
			System.out.println("service.buy(product " + purchaseQuantity + ") should throw InsufficientProductsException");
			
			service.buy(product, purchaseQuantity);
		} catch (InsufficientProductsException e) {
			
			System.out.println("InsufficientProductsException has been throw");
			
			verify(dao, times(0)).orderProduct(product, purchaseQuantity);
			
			System.out.println("Verified orderProduct(product, " + purchaseQuantity  + ") is not called");
			
			throw e;
		}
	}
	
}
