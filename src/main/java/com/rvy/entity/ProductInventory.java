package com.rvy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product_inventory_rvy")
public class ProductInventory implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inventoryId;
	
	@Column(columnDefinition = "NUMERIC(10,2)")
	private Double buyingPrice;

	@Column(columnDefinition = "NUMERIC(10,2)")
	private Double minimumSellingPrice;
	
	@NotBlank
	@Column(length = 50)
	private String batchcode;

	private LocalDate batchdate;

	@Column(columnDefinition = "NUMERIC(10)")
	private Integer quantity;

	@Column(columnDefinition = "NUMERIC(10)")
	private Integer reorderLevel;
	
	@Column(length = 50)
	private String status;

	private Integer storeId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)	
	@JoinColumn(name = "productId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Product product;
}
