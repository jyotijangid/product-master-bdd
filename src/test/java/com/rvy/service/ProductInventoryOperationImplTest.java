package com.rvy.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.dao.ProductInventoryRepository;
import com.rvy.dao.ProductMasterRepository;
import com.rvy.entity.Product;
import com.rvy.entity.ProductInventory;
import com.rvy.exception.ProductInventoryOperationException;
import com.rvy.exception.ProductOperationException;


@ExtendWith(SpringExtension.class)
public class ProductInventoryOperationImplTest {

	@TestConfiguration
	static class ProductInventoryServiceImplTestContextConfiguration {
		@Bean
		public ProductInventoryOperation productInventoryOperation() {
			return new ProductInventoryOperationImpl();
			}
		@Bean
		public ProductOperation productOperation() {
			return new ProductOperationsImpl();
		}
	}

//
//	@TestConfiguration
//	static class ProductInventoryServiceImplTestContextConfiguration1 {
//		@Bean
//		public ProductOperation productOperation() {
//			return new ProductOperationsImpl();
//		}
//	}


	@MockBean
	private ProductInventoryRepository productInventoryRepository;
	
	@MockBean
	private ProductMasterRepository productMasterRepository;
	
	@Autowired
	private ProductInventoryOperation productInventoryOperation;
	
	@Autowired
	private ProductOperation productOperation;

//	@MockBean
//	private ProductInventoryRepository productInventoryRepository;
//	private ProductMasterRepository productMasterRepository;

	private ProductInventory productInventory1;
	List<ProductInventory> productInventoryList;


	@BeforeEach
	public void setUp() {

		productInventory1  = new ProductInventory(null,50000.00,60000.00,"acsd",null,12,2,"avai",1,null);
		//		 product2  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S467",null,null);
		//		Product product3  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S787",null,null);

		productInventoryList = Arrays.asList(productInventory1);

		Mockito.when(productInventoryRepository.findAll()).thenReturn(productInventoryList);
		Mockito.when(productInventoryRepository.findById(productInventory1.getInventoryId())).thenReturn(Optional.of(productInventory1));
		Mockito.when(productInventoryRepository.findById(-1)).thenReturn(Optional.empty());
		Mockito.when(productInventoryRepository.save(productInventory1)).thenReturn(productInventory1);

	}
			
	@Test
	public void givenProductInventoryToAddShouldReturnAddedProductInventory() {
		try {
			ProductInventory productInventory = productInventoryOperation.addProductInventory(productInventory1);
			assertThat(productInventory).isEqualTo(productInventory1);
//			verify(productMasterRepository,times(1)).save(product1);
		} catch (ProductInventoryOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void whenValidId_ProductInventoryShouldBeFound() {
		try {
			ProductInventory productInventory = productInventoryOperation.getProductInventoryById(productInventory1.getInventoryId());
			assertThat(productInventory).isEqualTo(productInventory1);
		} catch (ProductInventoryOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void whenInValidId_ProductInventoryShouldNotBeFound() {
		try {
			ProductInventory productInventory = productInventoryOperation.getProductInventoryById(-1);
		} catch (ProductInventoryOperationException e) {
			// TODO Auto-generated catch block
			assertThat(e.getMessage()).isEqualTo("Unable to retreive Data or invalid Id");
		}
	}

	@Test
	public void whenFindAll_ReturnAll() {
		try {
			List<ProductInventory> productInventoryList1 = productInventoryOperation.getAllProductInventory();
			assertThat(productInventoryList1).isEqualTo(productInventoryList1);
		} catch (ProductInventoryOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void givenIdTODeleteThenShouldDeleteTheProduct() {
		try {
			Integer productSer = productInventoryOperation.deleteProductInventory(productInventory1.getInventoryId());
			assertThat(productSer).isEqualTo(productInventory1.getInventoryId());
//			verify(productMasterRepository,times(1)).save(product1);
		} catch (ProductInventoryOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
