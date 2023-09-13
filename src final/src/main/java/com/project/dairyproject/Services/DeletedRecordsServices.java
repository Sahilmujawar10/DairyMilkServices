package com.project.dairyproject.Services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dairyproject.Entities.ConsumerDetails;
import com.project.dairyproject.Entities.DeletedConsumerRecords;
import com.project.dairyproject.Entities.DeletedSellerRecords;
import com.project.dairyproject.Entities.SellerDetails;
import com.project.dairyproject.LoginEntities.Login;
import com.project.dairyproject.Repository.ConsumerRepository;
import com.project.dairyproject.Repository.DeletedConsumerRepository;
import com.project.dairyproject.Repository.DeletedSellerRepository;
import com.project.dairyproject.Repository.SellerRepository;

@Service
@Transactional
public class DeletedRecordsServices {

	@Autowired
	private ConsumerDetails conDetails;

	@Autowired
	private ConsumerRepository conRepo;

//	@Autowired
//	private DeletedConsumerRecords delConRecord;

	@Autowired
	private DeletedConsumerRepository delRepo;

	@Autowired
	private SellerDetails sellDetails;

	@Autowired
	private SellerRepository sellRepo;

//	@Autowired
//	private DeletedSellerRecords delSellRecord;

	@Autowired
	private DeletedSellerRepository delSellRepo;

	DeletedConsumerRecords delConRecord = new DeletedConsumerRecords();
	DeletedSellerRecords delSellRecord = new DeletedSellerRecords();

	public String deleteConsumerByEmailId(Login login) throws UnsupportedEncodingException {
		String encryptedPassword = Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8"));
		conDetails = conRepo.findConsumerDetailsByEmailAndPassword(login.getEmailId(), encryptedPassword);
		if (conDetails != null && conRepo.deleteConsumerDetailsByEmailId(conDetails.getEmailId()) == 1) {
			delConRecord.setAddress(conDetails.getAddress());
			delConRecord.setEmailId(conDetails.getEmailId());
			delConRecord.setFirstName(conDetails.getFirstName());
			delConRecord.setLastName(conDetails.getLastName());
			delConRecord.setGender(conDetails.getGender());
			delConRecord.setPhoneNumber(conDetails.getPhoneNumber());
			delConRecord.setConsumerId(conDetails.getConsumerId());
			delConRecord.setStreet(conDetails.getStreet());
			delConRecord.setUsername(conDetails.getUsername());
			delRepo.save(delConRecord);
			conDetails = null;
			delConRecord = null;
			return "Consumer account removed !";
		}

		return "Account not found !";

	}

	public String deleteConsumerByConsumerId(Integer consumerId) {
//		DeletedConsumerRecords delConRecord = new DeletedConsumerRecords();
		conDetails = conRepo.findConsumerDetailsByConsumerId(consumerId);
		if (conDetails != null && conRepo.deleteConsumerDetailsByConsumerId(conDetails.getConsumerId()) == 1) {
			delConRecord.setAddress(conDetails.getAddress());
			delConRecord.setEmailId(conDetails.getEmailId());
			delConRecord.setFirstName(conDetails.getFirstName());
			delConRecord.setLastName(conDetails.getLastName());
			delConRecord.setGender(conDetails.getGender());
			delConRecord.setPhoneNumber(conDetails.getPhoneNumber());
			delConRecord.setConsumerId(conDetails.getConsumerId());
			delConRecord.setStreet(conDetails.getStreet());
			delConRecord.setUsername(conDetails.getUsername());
			delRepo.save(delConRecord);
			conDetails = null;
			delConRecord = null;
			return "Consumer account removed !";
		}
		return "Account not found !";

	}

	public List<DeletedConsumerRecords> getDeletedAllConsumerRecords() {
		return delRepo.findAllDeletedConsumerRecords();
	}

	public List<DeletedSellerRecords> getDeletedAllSellerRecords() {
		return delSellRepo.findAllDeletedSellerRecords();
	}

	public DeletedConsumerRecords getDeletedConsumerRecordByEmailId(String emailId) {
		return delRepo.findDeletedConsumerByEmailId(emailId);
	}

	public List<DeletedConsumerRecords> getDeletedConsumerRecordsByFirstName(String name) {
		return delRepo.findDeletedConsumerRecordsByFirstName(name);
	}

	public String deleteSellerByEmailId(Login login) throws UnsupportedEncodingException {
		String encryptedPassword = Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8"));
		sellDetails = sellRepo.findSellerDetailsByEmailAndPassword(login.getEmailId(), encryptedPassword);
		if (sellDetails != null && sellRepo.deleteSellerDetailsByEmailId(sellDetails.getEmailId()) == 1) {
			delSellRecord.setAddress(sellDetails.getAddress());
			delSellRecord.setEmailId(sellDetails.getEmailId());
			delSellRecord.setFirstName(sellDetails.getFirstName());
			delSellRecord.setLastName(sellDetails.getLastName());
			delSellRecord.setGender(sellDetails.getGender());
			delSellRecord.setPhoneNumber(sellDetails.getPhoneNumber());
			delSellRecord.setSellerId(sellDetails.getSellerId());
			delSellRecord.setStreet(sellDetails.getStreet());
			delSellRecord.setUsername(sellDetails.getUsername());
			delSellRepo.save(delSellRecord);
			sellDetails = null;
			return "Consumer account removed !";
		}

		return "Account not found !";

	}

	public String deleteSellerrBySellerId(Integer sellerId) {
//		DeletedSellerRecords delSellRecord = new DeletedSellerRecords();
		sellDetails = sellRepo.findSellerDetailsBySellerId(sellerId);
		if (sellDetails != null && sellRepo.deleteSellerDetailsBySellerId(sellDetails.getSellerId()) == 1) {
			delSellRecord.setAddress(sellDetails.getAddress());
			delSellRecord.setEmailId(sellDetails.getEmailId());
			delSellRecord.setFirstName(sellDetails.getFirstName());
			delSellRecord.setLastName(sellDetails.getLastName());
			delSellRecord.setGender(sellDetails.getGender());
			delSellRecord.setPhoneNumber(sellDetails.getPhoneNumber());
			delSellRecord.setSellerId(sellDetails.getSellerId());
			delSellRecord.setStreet(sellDetails.getStreet());
			delSellRecord.setUsername(sellDetails.getUsername());
			delSellRepo.save(delSellRecord);
			conDetails = null;
			delSellRecord = null;
			return "Seller account removed !";
		}
		return "Account not found !";

	}

	public DeletedSellerRecords getDeletedSellerRecordByEmailId(String emailId) {
		return delSellRepo.findDeletedSellerRecordsByEmailId(emailId);
	}

	public List<DeletedSellerRecords> getDeletedSellerRecordsByFirstName(String name) {
		return delSellRepo.findDeletedSellerRecordsByFirstName(name);
	}
}
