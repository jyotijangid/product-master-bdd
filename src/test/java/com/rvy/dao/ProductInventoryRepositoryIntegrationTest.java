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
import com.rvy.entity.ProductInventory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProductMasterApplication.class })



@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class ProductInventoryRepositoryIntegrationTest {

	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private ProductInventoryRepository productInventoryRepo;
	
	@Test
	public void whenFindById_returnId() {
		
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, null, null, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		ProductInventory productInventoryDb = productInventoryRepo.findById(productInventory.getInventoryId()).orElse(null);
		
		assertThat(productInventoryDb.getInventoryId()).isEqualTo(productInventory.getInventoryId());
		
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		ProductInventory productInventoryDb = productInventoryRepo.findById((int) -11l).orElse(null);
        assertThat(productInventoryDb).isNull();
	}
	
	@Test
	public void whenFindAll_thenReturnAll() {
		
		ProductInventory productInventory1 = new ProductInventory(null, 20.0, 23.0, null, null, 10, 5, "Available", null, null);
		ProductInventory productInventory2 = new ProductInventory(null, 21.0, 24.0, null, null, 100, 10, "Not Available", null, null);
		ProductInventory productInventory3 = new ProductInventory(null, 22.0, 25.0, null, null, 20, 0, "Not Available", null, null);
		
		entityManager.persist(productInventory1);
		entityManager.persist(productInventory2);
		entityManager.persist(productInventory3);
		
		List<ProductInventory> allProducts = productInventoryRepo.findAll();
		entityManager.flush();
		
			assertThat(allProducts).hasSize(3);
		
	}
	
	
	@Test
	public void whenFindByType_thenReturnType() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, null, null, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByBuyingPrice(productInventory.getBuyingPrice());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getBuyingPrice()).isEqualTo(productInventory.getBuyingPrice());
        }else {
        	assertThat(allProducts.get(0).getBuyingPrice()).isNotEqualTo(productInventory.getBuyingPrice());
        }
	}
	
	@Test
	public void whenFindByInvalidType_thenReturnNull() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, null, null, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByBuyingPrice(0.0);
		
		assertThat(allProducts).isEmpty();
	}
	
	
	@Test
	public void whenFindByStatus_thenReturnStatus() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, null, null, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByStatus(productInventory.getStatus());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getStatus()).isEqualTo(productInventory.getStatus());
        }else {
        	assertThat(allProducts.get(0).getStatus()).isNotEqualTo(productInventory.getStatus());
        }
	}
	
	@Test
	public void whenFindByInvalidStatus_thenReturnNull() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, null, null, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByStatus("Not Available");
		
		assertThat(allProducts).isEmpty();
	}
	
	@Test
	public void whenFindByReorderLevel_thenReturnReorderLevel() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, 20, 5, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByReorderLevel(productInventory.getReorderLevel());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getReorderLevel()).isEqualTo(productInventory.getReorderLevel());
        }else {
        	assertThat(allProducts.get(0).getReorderLevel()).isNotEqualTo(productInventory.getReorderLevel());
        }
	}
	
	@Test
	public void whenFindByInvalidReorderLevel_thenReturnNull() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, 20, 5, "Available", null, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByReorderLevel(10);
		
		assertThat(allProducts).isEmpty();
	}
	
	
	@Test
	public void whenFindByStoreId_thenReturnStoreId() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, 20, 5, "Available", 100, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByStoreId(productInventory.getStoreId());
		
		if(allProducts.size()!=0) {
        	assertThat(allProducts.get(0).getStoreId()).isEqualTo(productInventory.getStoreId());
        }else {
        	assertThat(allProducts.get(0).getStoreId()).isNotEqualTo(productInventory.getStoreId());
        }
	}
	
	@Test
	public void whenFindByInvalidStoreId_thenReturnNull() {
		ProductInventory productInventory = new ProductInventory(null, 20.0, 23.0, null, null, 20, 5, "Available", 100, null);
		entityManager.persistAndFlush(productInventory);
		
		List<ProductInventory> allProducts = productInventoryRepo.findByStoreId(10);
		
		assertThat(allProducts).isEmpty();
	}
	
	
}
