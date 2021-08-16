package com.rvy.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
@TypeDef(name = "json", typeClass = JsonType.class)
@Table (name = "product_rvy")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotBlank
	@Column(length = 50)
	private String category;
	
	@NotBlank
	@Column(length = 50)
	private String type;
	
	@NotBlank
	@Column(length = 50)
	private String brand;
	
	@NotBlank
	@Column(length = 100)
	private String productName;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private Byte[] image;
	
	@Type(type = "json")
	@Column(columnDefinition = "jsonb")
	private ProductDesc productDesc;
	
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "product",fetch= FetchType.LAZY)
//	private List<ProductInventory> productInventory;
	
}
