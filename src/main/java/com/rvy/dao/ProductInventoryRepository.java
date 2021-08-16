package com.rvy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rvy.entity.ProductInventory;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {

	List<ProductInventory> findByStoreId(Integer storeId);
	List<ProductInventory> findByStatus(String status);
	List<ProductInventory> findByBuyingPrice(Double buyingPrice);
	List<ProductInventory> findByReorderLevel(Integer reorderLevel);
}
