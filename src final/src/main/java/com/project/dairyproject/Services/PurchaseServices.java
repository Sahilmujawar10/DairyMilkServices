package com.project.dairyproject.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dairyproject.Entities.ConfirmPurchaseOrder;
import com.project.dairyproject.Entities.ConsumerDetails;
import com.project.dairyproject.Entities.ProductDetails;
import com.project.dairyproject.Entities.PurchaseDetails;
import com.project.dairyproject.Entities.SellerDetails;
import com.project.dairyproject.Repository.PurchaseRecordRepository;

@Service
@Transactional
public class PurchaseServices {

	@Autowired
	private PurchaseRecordRepository purchaseRepo;

	@Autowired
	private ConsumerDetails conDetails;

	@Autowired
	private ConsumerServices conServ;

	@Autowired
	private SellerDetails sellDetails;

	@Autowired
	private SellerServices sellServ;

	@Autowired
	private ProductDetails proDetails;

	@Autowired
	private ProductServices proServ;

	@Autowired
	private PurchaseDetails purchaseDetails;

	@Autowired
	private PurchaseRecordRepository purRepo;

	long transactionId = 4567895;

	public List<PurchaseDetails> getAllPurchaseRecords() {
		return purchaseRepo.findAllPurchaseDetails();
	}

	public List<PurchaseDetails> getPurchaseDetailsByConsumerEmailId(String emailId) {
		return purchaseRepo.findAllPurchaseDetailsByConsumerEmailId(emailId);
	}

	public List<PurchaseDetails> getPurchaseDetailsBySellerEmailId(String emailId) {
		return purchaseRepo.findAllPurchaseDetailsBySellerEmailId(emailId);
	}

	public PurchaseDetails getPurchaseDetailsFromPurchaseId(Integer purchaseId) {
		return purchaseRepo.findPurchaseDetailsByPurchaseId(purchaseId);
	}

	public PurchaseDetails insertPurchaseRecord(ConfirmPurchaseOrder confirmPurchaseOrder) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		String dateTime = dtf.format(LocalDateTime.now());

		ProductDetails productDetails = proServ.getProductDetailsByName(confirmPurchaseOrder.getProductName());
		ConsumerDetails consumerDetails = conServ
				.getConsumerDetailsByEmailId(confirmPurchaseOrder.getConsumerEmailId());
		SellerDetails sellerDetails = sellServ.getSellerDetailsByEmailId(confirmPurchaseOrder.getSellerEmailId());
		PurchaseDetails purDetails = new PurchaseDetails();
		purDetails.setConsumerDetails(consumerDetails);
		purDetails.setSellerDetails(sellerDetails);
		purDetails.setProductDetails(productDetails);
		purDetails.setQuantity(confirmPurchaseOrder.getQuantity());
		purDetails.setTotalPrice((productDetails.getPrice() * confirmPurchaseOrder.getQuantity()));
		purDetails.setPaymentMode(confirmPurchaseOrder.getPaymentMode());
		purDetails.setTransactionId(++transactionId);
		purDetails.setDateTime(dateTime);
		purDetails.setStatus("Placed");

		PurchaseDetails purchaseDetails = purchaseRepo.save(purDetails);

		productDetails = null;
		consumerDetails = null;
		sellerDetails = null;
		purDetails = null;

		return purchaseDetails;

	}

	public void changeDeliveryStatusBySeller(Integer purchaseId, String status) {
		purchaseDetails = purRepo.findPurchaseDetailsByPurchaseId(purchaseId);
		purchaseDetails.setStatus(status);
		purchaseRepo.save(purchaseDetails);
		purchaseDetails = null;

	}
}
