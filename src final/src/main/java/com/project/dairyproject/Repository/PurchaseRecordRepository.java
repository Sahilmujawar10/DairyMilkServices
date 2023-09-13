package com.project.dairyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.PurchaseDetails;

@Repository
public interface PurchaseRecordRepository extends CrudRepository<PurchaseDetails, Integer> {

	@Query("select p from PurchaseDetails p")
	public List<PurchaseDetails> findAllPurchaseDetails();

	@Query("select p from PurchaseDetails p where consumerDetails.emailId = ?1")
	public List<PurchaseDetails> findAllPurchaseDetailsByConsumerEmailId(String emailId);

	@Query("select p from PurchaseDetails p where sellerDetails.emailId = ?1")
	public List<PurchaseDetails> findAllPurchaseDetailsBySellerEmailId(String emailId);

	@Query("select p from PurchaseDetails p where p.purchaseId = ?1")
	public PurchaseDetails findPurchaseDetailsByPurchaseId(Integer purchaseId);

}
