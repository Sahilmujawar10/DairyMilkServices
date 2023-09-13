package com.project.dairyproject.Entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int PID;

	@NotEmpty(message = "Product Name is required")
	@Column(length = 15, unique = true)
	@Pattern(regexp = "^[a-zA-Z_ ]{4,20}$", message = "Product name must be in betweem 4 to 20 (special characters are not allowed)")
	private String name;

	@NotNull(message = "Price cannot be empty, please enter price for your product")
	@Range(min = 8, message = "Price should be more than 8 Rs.")
	private float price;

	@NotEmpty(message = "Unit cannot be empty. Please select valid unit for selected product.")
	@Pattern(regexp = "^(L|kg)?", message = "Incorrect unit selected")
	private String unit;

	@ManyToMany(mappedBy = "productDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SellerDetails> sellerDetails;

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Set<SellerDetails> getSellerDetails() {
		return sellerDetails;
	}

	public void setSellerDetails(Set<SellerDetails> sellerDetails) {
		this.sellerDetails = sellerDetails;
	}

}
