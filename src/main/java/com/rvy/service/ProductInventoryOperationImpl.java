package com.rvy.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvy.dao.ProductInventoryRepository;
import com.rvy.dao.ProductMasterRepository;
import com.rvy.entity.Product;
import com.rvy.entity.ProductInventory;
import com.rvy.exception.ProductOperationException;
import com.rvy.exception.ProductInventoryOperationException;

@Service
@Transactional
public class ProductInventoryOperationImpl implements ProductInventoryOperation{


	@Autowired
	private ProductInventoryRepository productInventoryRepo;
	
	@Autowired
	private ProductMasterRepository productMasterRepo;


	@Override
	public List<ProductInventory> getAllProductInventory() throws ProductInventoryOperationException {
		// TODO Auto-generated method stub
		try {
			return productInventoryRepo.findAll();
		}catch (PersistenceException e) {
			throw new ProductInventoryOperationException("Unable to retreive Data or not found");
		}

	}

	@Override
	public ProductInventory getProductInventoryById(Integer Id) throws ProductInventoryOperationException {
		// TODO Auto-generated method stub
		try {
			return productInventoryRepo.findById(Id).get();
		}catch (Exception e) {
			throw new ProductInventoryOperationException("Unable to retreive Data or invalid Id");
		}

	}

	@Override
	public ProductInventory addProductInventory(ProductInventory productInventory)
			throws ProductInventoryOperationException {
		// TODO Auto-generated method stub
		try {
			return productInventoryRepo.save(productInventory);
		}catch (Exception e) {
			throw new ProductInventoryOperationException(e.getMessage(),e);
		}

	}

	@Override
	public ProductInventory updateProductInventory(ProductInventory productInventory)
			throws ProductInventoryOperationException {
		// TODO Auto-generated method stub
		try {
		ProductInventory productInventory1 = productInventoryRepo.findById(productInventory.getInventoryId()).get();
		if(productInventory1 == null) {
			throw new ProductInventoryOperationException();
		}
		return productInventoryRepo.save(productInventory);
		} catch(Exception e) {
			throw new ProductInventoryOperationException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteProductInventory(Integer id) throws ProductInventoryOperationException {
		// TODO Auto-generated method stub
		try {
			ProductInventory productInventory1 = productInventoryRepo.findById(id).get();
			 productInventoryRepo.delete(productInventory1);
			 return id;
		}catch (Exception e) {
			throw new ProductInventoryOperationException("Delete Operation failed no such Id found");
		}
	}

	@Override
	public List<Product> getProductsByInvenroryId(List<Integer> idList) throws ProductInventoryOperationException {
		try {
			List<Product> products = null;
			products =idList.stream().map((id)-> productInventoryRepo.getById(id).getProduct()).collect(Collectors.toList());
			return products;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductInventoryOperationException(e.getMessage(),e);
		}
	}

	@Override
	public Product getProductByInventoryId(Integer Id) throws ProductInventoryOperationException {
		
		try {
			return productInventoryRepo.findById(Id).get().getProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductInventoryOperationException(e.getMessage(),e);
		}
	}


}
