package com.rvy.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.dao.ProductMasterRepository;
import com.rvy.entity.Product;
import com.rvy.exception.ProductOperationException;



@ExtendWith(SpringExtension.class)
public class ProductOperationsImplTest {
	
	@TestConfiguration
	static class PeoducttServiceImplTestContextConfiguration {
		
		@Bean
		public ProductOperation productOperation() {
			return new ProductOperationsImpl();
		}
	}

	@Autowired
	private ProductOperation productOperation;
	
	@MockBean
	private ProductMasterRepository productMasterRepository;
	
	private Product product1,product2;
	List<Product> allProducts;
	
	@BeforeEach
	public void setUp() {
		
		 product1  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S340",null,null);
		 product2  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S467",null,null);
		Product product3  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S787",null,null);
		
		 allProducts = Arrays.asList(product1,product2,product3);
		
		Mockito.when(productMasterRepository.findAll()).thenReturn(allProducts);
		Mockito.when(productMasterRepository.findById(product1.getProductId())).thenReturn(Optional.of(product1));
		Mockito.when(productMasterRepository.findById(-11)).thenReturn(Optional.empty());
		Mockito.when(productMasterRepository.save(product1)).thenReturn(product1);

	}
	
	@Test
	public void whenValidId_ProduceShouldBeFound() {
		try {
			Product productSer = productOperation.getDetailsById(product1.getProductId());
			assertThat(productSer).isEqualTo(product1);
		} catch (ProductOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	@Test
	public void whenInValidId_ReturnNull() {
		try {
			Product productSer = productOperation.getDetailsById(-11);
			assertThat(productSer).isNull();
		} catch (ProductOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	@Test
	public void whenFindAll_ReturnAll() {
		try {
			List<Product> productSer = productOperation.getAllProducts();
			assertThat(productSer).isEqualTo(allProducts);
		} catch (ProductOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenProductToAddShouldReturnAddedProduct() {
		try {
			Product productSer = productOperation.addProduct(product1);
			assertThat(productSer).isEqualTo(product1);
//			verify(productMasterRepository,times(1)).save(product1);
		} catch (ProductOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void givenIdTODeleteThenShouldDeleteTheProduct() {
		try {
			Integer productSer = productOperation.deleteProduct(product1.getProductId());
			assertThat(productSer).isEqualTo(product1.getProductId());
//			verify(productMasterRepository,times(1)).save(product1);
		} catch (ProductOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
