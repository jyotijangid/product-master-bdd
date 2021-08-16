package com.rvy.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvy.dao.ProductMasterRepository;
import com.rvy.entity.Product;
import com.rvy.exception.ProductOperationException;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
@Transactional
public class ProductOperationsImpl implements ProductOperation{
	

	@Autowired
	private ProductMasterRepository productMasterRepo;
	
	@Override
	public List<Product> getAllProducts() throws ProductOperationException {
	
		try {
			return productMasterRepo.findAll();
		} catch (PersistenceException e) {
			throw new ProductOperationException("Unable to retreive Data or no data found");
		}
	}

	@Override
	public Product getDetailsById(Integer id) throws ProductOperationException {
		
		try {
			return productMasterRepo.findById(id).get();
		} catch (Exception e) {
			throw new ProductOperationException("Unable to retreive Data or invalid Id");
		}
	}

	@Override
	public Product updateProduct(Product product) throws ProductOperationException{
		
		try {
			Product product1=productMasterRepo.findById(product.getProductId()).get();
			if(product1==null) {
				throw new ProductOperationException("Illegal operation no such id exists updation failed");
			}
			return productMasterRepo.save(product);
		} catch (Exception e) {
			throw new ProductOperationException(e.getMessage(),e);
		}
		 
	}

	@Override
	public Product addProduct(Product product) throws ProductOperationException{
		try {
//			Product product1=productMasterRepo.findById(product.getProductId()).get();
//			if(product1!=null) {
//				throw new ProductOperationException("Illegal operation id already exists");
//			}
			 return productMasterRepo.save(product);
		} catch (Exception e) {
		 throw new ProductOperationException(e.getMessage(),e);
		}
		
	}

	@Override
	public Integer deleteProduct(Integer id) throws ProductOperationException{
		try {
			Product product = productMasterRepo.findById(id).get();
			productMasterRepo.delete(product);
			return id;
		} catch (Exception e) {
			throw new ProductOperationException("Delete Operation failed no such Id found");
		}

	}

	@Override
	public List<Product> getDetailsByAttribute(String brand, String type, String category)
			throws ProductOperationException {
		try {
		    System.out.println(brand+" ,"+type+", "+category);
			List<Product> products;
			if(brand== "null" && type == "null" && category == "null") {
				throw new ProductOperationException("Search failed due to invalid or insufficient inputs");
			}
			else if(brand!="null") {
				System.out.println("inside brand");
				products = productMasterRepo.findByBrand(brand);
				products.stream().forEach((p)->System.out.println(p));
				return products;
//				if(type=="null" && category == "null") {
//					products.stream().forEach(System.out::println);
//					return products;
//				}
//				else if(type !="null" && category=="null") {
//					return products.stream().filter((p)-> p.getType().equals(type)).collect(Collectors.toList());
//				}
//				else if(type=="null" && category!= "null") {
//					return products.stream().filter((p)-> p.getCategory().equals(category)).collect(Collectors.toList());
//				}
//				else
//				{
//					return products.stream().filter((p)-> p.getType().equals(type) && p.getCategory().equals(category)).collect(Collectors.toList());
//				}
			}
			else {
				if(type!="null") {
					products = productMasterRepo.findByType(type);
					if(category=="null") {
						return products;
					}
					else {
						return products.stream().filter((p)-> p.getCategory().equals(category)).collect(Collectors.toList());
					}
				}
				else {
					products = productMasterRepo.findByCategory(category);
					return products;
				}
			}
		} catch (Exception e) {
			
			throw new ProductOperationException(e.getMessage(),e);
		}
		
	}

//	@Override
//	public List<Product> getProductsByType(String type) throws ProductOperationException {
//		try {
//			return productMasterRepo.findProductByType(type);
//		} catch (Exception e) {
//			throw new ProductOperationException(e.getMessage(),e);
//		}
		
	//}
}