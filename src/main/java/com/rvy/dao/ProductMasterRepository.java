package com.rvy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rvy.entity.Product;

@Repository
public interface ProductMasterRepository extends JpaRepository<Product,Integer> {
	
	
//	@Query("select p from product_master r where type= :getType")
	List<Product> findByType(String type);
	List<Product> findByProductName(String productName);
	List<Product> findByBrand(String brand);
	List<Product> findByCategory(String category);

}
