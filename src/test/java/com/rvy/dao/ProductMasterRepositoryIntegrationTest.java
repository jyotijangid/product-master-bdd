package com.rvy.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.ProductMasterApplication;
import com.rvy.entity.Product;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProductMasterApplication.class })



@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class ProductMasterRepositoryIntegrationTest {

	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	
	
	@Test
	public void whenFindById_thenReturnType() {
		Product product  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S340",null,null);
		entityManager.persistAndFlush(product);
		
		Product productDb = productMasterRepository.findById(product.getProductId()).orElse(null);
		
		 assertThat(productDb.getProductId()).isEqualTo(product.getProductId());
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		Product productDb = productMasterRepository.findById((int) -11l).orElse(null);
        assertThat(productDb).isNull();
	}
	
	
	@Test
	public void whenFindByType_thenReturnType() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByType(product.getType());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getType()).isEqualTo(product.getType());
        }else {
        	assertThat(allProducts.get(0).getType()).isNotEqualTo(product.getType());
        }
	}
	
	@Test
	public void whenFindByInvalidType_thenReturnNull() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByType("Invalid Type");
		
		assertThat(allProducts).isEmpty();
	}
	
	@Test
	public void whenFindByProductName_thenReturnProductName() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByProductName(product.getProductName());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getProductName()).isEqualTo(product.getProductName());
        }else {
        	assertThat(allProducts.get(0).getProductName()).isNotEqualTo(product.getProductName());
        }
	}
	
	@Test
	public void whenFindByInvalidProductName_thenReturnNull() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByProductName("Invalid name");
		
		assertThat(allProducts).isEmpty();
	}
	
	@Test
	public void whenFindByBrand_thenReturnBrand() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByBrand(product.getBrand());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getBrand()).isEqualTo(product.getBrand());
        }else {
        	assertThat(allProducts.get(0).getBrand()).isNotEqualTo(product.getBrand());
        }
	}
	
	@Test
	public void whenFindByInvalidBrand_thenReturnNull() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByBrand("Invalid brand");
		
		assertThat(allProducts).isEmpty();
	}
	
	@Test
	public void whenFindByCategory_thenReturnBrand() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByCategory(product.getCategory());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getCategory()).isEqualTo(product.getCategory());
        }else {
        	assertThat(allProducts.get(0).getCategory()).isNotEqualTo(product.getCategory());
        }
	}
	
	@Test
	public void whenFindByInvalidCategory_thenReturnNull() {
		Product product  = new Product(null,"Electronics","Mobile","Samsung","Galaxy",null,null);
		entityManager.persistAndFlush(product);
		
		List<Product> allProducts = productMasterRepository.findByCategory("Invalid brand");
		
		assertThat(allProducts).isEmpty();
	}
	
	
	
	@Test
	public void whenFindAll_thenReturnAllProducts() {
		Product product1  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S340",null,null);
		Product product2  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S467",null,null);
		Product product3  = new Product(null,"Electronics","laptop","Lenovo","Ideapad S787",null,null);
		
		List<Product> allProducts1 = productMasterRepository.findAll();
		
		entityManager.persist(product1);
		entityManager.persist(product2);
		entityManager.persist(product3);
		
		List<Product> allProducts = productMasterRepository.findAll();
		
		entityManager.flush();
		
		 assertThat(allProducts).hasSize(3+allProducts1.size());
		
		
		
	}
	
}
