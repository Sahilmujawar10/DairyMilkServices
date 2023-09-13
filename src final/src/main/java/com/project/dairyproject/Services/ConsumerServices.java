package com.project.dairyproject.Services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import javax.swing.event.MenuKeyEvent;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.dairyproject.Entities.AddressDetails;
import com.project.dairyproject.Entities.ConsumerDetails;
import com.project.dairyproject.Entities.DeletedConsumerRecords;
import com.project.dairyproject.LoginEntities.ChangePassword;
import com.project.dairyproject.LoginEntities.Login;
import com.project.dairyproject.Repository.AddressRepository;
import com.project.dairyproject.Repository.ConsumerRepository;
import com.project.dairyproject.Repository.DeletedConsumerRepository;
import com.project.dairyproject.UserDefinedExceptions.ConsumerNotFoundException;
import com.project.dairyproject.UserDefinedExceptions.EmailAddressFoundException;
import com.project.dairyproject.UserDefinedExceptions.IncorrectPasswordException;
import com.project.dairyproject.UserDefinedExceptions.PhoneNumberFoundException;
import com.project.dairyproject.UserDefinedExceptions.UnmatchedPasswordException;
import com.project.dairyproject.UserDefinedExceptions.UsernameFoundException;

@Service
@Transactional
@Component
public class ConsumerServices {

	@Autowired
	private ConsumerRepository conRepo;

	@Autowired
	private AddressDetails addressDetails;

	@Autowired
	private AddressRepository addRepo;

	@Autowired
	private ConsumerDetails conDetails;

//	@Autowired
//	private DeletedConsumerRecords delConRecord;
//
//	@Autowired
//	private DeletedConsumerRepository delRepo;

	@Autowired
	private DeletedRecordsServices delServ;

	public ConsumerDetails registerNewConsumer(ConsumerDetails consumerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		int email = conRepo.findConsumerDetailsByEmailId(consumerDetails.getEmailId());
		int username = conRepo.findConsumerDetailsByUsername(consumerDetails.getUsername());
		int phoneNumber = conRepo.findConsumerDetailsByPhoneNumber(consumerDetails.getPhoneNumber());

		addressDetails = addRepo.findAddressDetailsByPincode(consumerDetails.getAddress().getPincode());

		if (email == 1) {
			throw new EmailAddressFoundException("Email address already registered");
		} else if (username == 1) {
			throw new UsernameFoundException("Username already taken, please try with another one");
		} else if (phoneNumber == 1) {
			throw new PhoneNumberFoundException("Phone Number already registered");
		} else if (addressDetails != null) {
			consumerDetails.setAddress(addressDetails);
			conRepo.save(consumerDetails);
			addressDetails = null;
			return conRepo.findConsumerDetailsByEmailAndPassword(consumerDetails.getEmailId(),
					consumerDetails.getPassword());
		} else {
			conRepo.save(consumerDetails);
			return conRepo.findConsumerDetailsByEmailAndPassword(consumerDetails.getEmailId(),
					consumerDetails.getPassword());
		}

	}

	public ConsumerDetails getConsumerDetailsByEmailAndPassword(String emailId, String password)
			throws UnsupportedEncodingException {
		String encryptPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
		conDetails = conRepo.findConsumerDetailsByEmailAndPassword(emailId, encryptPassword);
		if (conDetails != null) {
			return conDetails;
		}
		throw new ConsumerNotFoundException("Consumer not found !");
	}

	public ConsumerDetails getConsumerDetailsByUsernameAndPassword(String username, String password) {
		return conRepo.findConsumerDetailsByUsernameAndPassword(username, password);
	}

	public ConsumerDetails getConsumerDetailsByPhoneNumber(String phoneNumber, String password) {
		return conRepo.findConsumerDetailsByPhoneNumberAndPassword(phoneNumber, password);
	}

	public ConsumerDetails getConsumerDetailsByEmailId(String emailId) {
		return conRepo.findConsumerDetailsByEmailIdOnly(emailId);
	}

	public ConsumerDetails getConsumerDetailsByUsername(String username) {
		return conRepo.findConsumerDetailsByUsernameOnly(username);
	}

	public ConsumerDetails getConsumerDetailsByPhoneNumer(String phoneNumber) {
		return conRepo.findConsumerDetailsByPhoneNumberOnly(phoneNumber);
	}

	public String deleteConsumerDetailsByEmailId(Login login) throws UnsupportedEncodingException {
		return delServ.deleteConsumerByEmailId(login);
	}

	public String deleteConsumerDetailsByConsumerId(Integer consumerId) {
		return delServ.deleteConsumerByConsumerId(consumerId);
	}

	public List<ConsumerDetails> getAllConsumerList() {
		return conRepo.findAllConsumerDetails();
	}

	public List<ConsumerDetails> getConsumerDetailsByFirstName(String firstName) {
		return conRepo.findConsumerByName(firstName);
	}

	public List<ConsumerDetails> getConsumerListByPincode(String pincode) {
		return conRepo.findConsumersByPincode(pincode);
	}

	public List<ConsumerDetails> getConsumerListByDistrict(String district) {
		return conRepo.findConsumersByDistrict(district);
	}

	public List<ConsumerDetails> getConsumerListByTown(String town) {
		return conRepo.findConsumersByTown(town);
	}

	public List<ConsumerDetails> getConsumerByAid(Integer aid) {
		return conRepo.findConsumerDetailsByAid(aid);
	}

	public ConsumerDetails updateConsumerDetails(ConsumerDetails consumerDetails) throws UnsupportedEncodingException {
		ConsumerDetails conDetails = conRepo.findConsumerDetailsByEmailIdOnly(consumerDetails.getEmailId());

		addressDetails = addRepo.findAddressDetailsByPincode(consumerDetails.getAddress().getPincode());
		if (addressDetails != null) {
			conDetails.setAddress(addressDetails);
		} else {
			addRepo.save(consumerDetails.getAddress());
			addressDetails = addRepo.findAddressDetailsByPincode(consumerDetails.getAddress().getPincode());
			conDetails.setAddress(addressDetails);
		}

		conDetails.setStreet(consumerDetails.getStreet());
		conDetails.setFirstName(consumerDetails.getFirstName());
		conDetails.setLastName(consumerDetails.getLastName());
		conDetails.setPhoneNumber(consumerDetails.getPhoneNumber());
//		conDetails.setUsername(consumerDetails.getUsername());
		conRepo.save(conDetails);
		conDetails = null;

		return conRepo.findConsumerDetailsByEmailAndPassword(consumerDetails.getEmailId(),
				consumerDetails.getPassword());

	}

	public String changeConsumerPassword(ChangePassword changePassword) throws UnsupportedEncodingException {
		String encryptPassword = Base64.getEncoder().encodeToString(changePassword.getOldPassword().getBytes("UTF-8"));
		conDetails = conRepo.findConsumerDetailsByEmailAndPassword(changePassword.getEmailId(), encryptPassword);
		if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
			if (conDetails != null) {
				conDetails.setPassword(changePassword.getNewPassword());
				conRepo.save(conDetails);
				conDetails = null;
				return "Password changed successfully.";
			} else {
				throw new IncorrectPasswordException("Incorrect old password !");
			}

		} else {
			throw new UnmatchedPasswordException("Password does not match. Please enter correct password !");
		}

	}

}
