package com.project.dairyproject.Entities;

import java.util.Objects;
import java.util.Set;

public class SellerProducts {
	private String emailId;
	private String password;

	private Set<String> productDetails;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<String> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailId, password, productDetails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SellerProducts)) {
			return false;
		}
		SellerProducts other = (SellerProducts) obj;
		return Objects.equals(emailId, other.emailId) && Objects.equals(password, other.password)
				&& Objects.equals(productDetails, other.productDetails);
	}

}
