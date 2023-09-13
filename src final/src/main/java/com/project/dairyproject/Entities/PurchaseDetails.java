package com.project.dairyproject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class PurchaseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;

	@OneToOne
	@JoinColumn(name = "consumerId")
	private ConsumerDetails consumerDetails;

	@OneToOne
	@JoinColumn(name = "sellerId")
	private SellerDetails sellerDetails;

	@OneToOne
	@JoinColumn(name = "PID")
	private ProductDetails productDetails;

	@NotNull(message = "Quantity cannot be empty !")
	private int quantity;

	@NotNull(message = "Total Price cannot be empty !")
	private float totalPrice;

	@NotEmpty(message = "Select proper payment mode.")
	@Pattern(regexp = "^(UPI|Credit|Debit|COD)$", message = "Incorrect payment method")
	private String paymentMode;

	@NotNull(message = "Transactional ID cannot be null !")
	private long transactionId;

	private String dateTime;

	@NotEmpty(message = "Purchase Status cannot be empty !")
	@Pattern(regexp = "^(Placed|Shipped|Delivered)$", message = "Improper purchase status !")
	private String status;

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public ConsumerDetails getConsumerDetails() {
		return consumerDetails;
	}

	public void setConsumerDetails(ConsumerDetails consumerDetails) {
		this.consumerDetails = consumerDetails;
	}

	public SellerDetails getSellerDetails() {
		return sellerDetails;
	}

	public void setSellerDetails(SellerDetails sellerDetails) {
		this.sellerDetails = sellerDetails;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
