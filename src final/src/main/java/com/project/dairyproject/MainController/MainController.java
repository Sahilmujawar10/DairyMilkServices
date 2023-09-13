package com.project.dairyproject.MainController;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dairyproject.Entities.Administrator;
import com.project.dairyproject.Entities.ConfirmPurchaseOrder;
import com.project.dairyproject.Entities.ConsumerDetails;
import com.project.dairyproject.Entities.ConsumerQuery;
import com.project.dairyproject.Entities.ConsumerQueryMessage;
import com.project.dairyproject.Entities.DeletedConsumerRecords;
import com.project.dairyproject.Entities.DeletedSellerRecords;
import com.project.dairyproject.Entities.FeedBackDetails;
import com.project.dairyproject.Entities.ProductDetails;
import com.project.dairyproject.Entities.PurchaseDetails;
import com.project.dairyproject.Entities.SellerDetails;
import com.project.dairyproject.Entities.SellerProducts;
import com.project.dairyproject.Entities.SellerQuery;
import com.project.dairyproject.LoginEntities.ChangePassword;
import com.project.dairyproject.LoginEntities.Login;
import com.project.dairyproject.LoginEntities.LoginByPhone;
import com.project.dairyproject.LoginEntities.LoginByUsername;
import com.project.dairyproject.Repository.PurchaseRecordRepository;
import com.project.dairyproject.Services.AdminServices;
import com.project.dairyproject.Services.ConsumerServices;
import com.project.dairyproject.Services.DeletedRecordsServices;
import com.project.dairyproject.Services.FeedBackServices;
import com.project.dairyproject.Services.ProductServices;
import com.project.dairyproject.Services.PurchaseServices;
import com.project.dairyproject.Services.QueryServices;
import com.project.dairyproject.Services.SellerServices;
import com.project.dairyproject.UserDefinedExceptions.EmailAddressFoundException;
import com.project.dairyproject.UserDefinedExceptions.PhoneNumberFoundException;
import com.project.dairyproject.UserDefinedExceptions.UsernameFoundException;

import net.bytebuddy.asm.Advice.Return;

@RestController
@CrossOrigin
public class MainController {

	@Autowired
	private AdminServices adminServ;

	@Autowired
	private ConsumerServices conServ;

	@Autowired
	private SellerServices sellServ;

	@Autowired
	private DeletedRecordsServices delServ;

	@Autowired
	private FeedBackServices feedServ;

	@Autowired
	private QueryServices queryServ;

	@Autowired
	private ProductServices proServ;

	@Autowired
	private PurchaseServices purchaseServ;

	// Consumers Controller

	@PostMapping("/consumer/registerdetails")
	public ConsumerDetails registerNewConsumer(@Valid @RequestBody ConsumerDetails consumerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		return conServ.registerNewConsumer(consumerDetails);
	}

	@PostMapping("/consumer/fetchdetailsbyemail")
	public ConsumerDetails getConsumerDetailsByEmailIdandPassword(@RequestBody Login login)
			throws UnsupportedEncodingException {
		return conServ.getConsumerDetailsByEmailAndPassword(login.getEmailId(), login.getPassword());
	}
	/*
	 * @PostMapping("/consumer/fetchdetailsbyusername") public ConsumerDetails
	 * getConsumerDetailsByUsernameAndPassword(@RequestBody LoginByUsername
	 * loginByUsername) { return
	 * conServ.getConsumerDetailsByUsernameAndPassword(loginByUsername.getUsername()
	 * , loginByUsername.getPassword()); }
	 * 
	 * @PostMapping("/consumer/fetchdetailsbyphonenumber") public ConsumerDetails
	 * getConsumerDetailsByPhoneNumber(@RequestBody LoginByPhone loginByPhone) {
	 * return conServ.getConsumerDetailsByPhoneNumber(loginByPhone.getPhoneNumber(),
	 * loginByPhone.getPassword()); }
	 */

	@PostMapping("/consumer/updatedetails")
	public ConsumerDetails updateConsumerDetails(@RequestBody ConsumerDetails consumerDetails)
			throws UnsupportedEncodingException {
		return conServ.updateConsumerDetails(consumerDetails);
	}

	@PostMapping("/consumer/changepassword")
	public String changeConsumerPassword(@RequestBody ChangePassword changePassword)
			throws UnsupportedEncodingException {
		return conServ.changeConsumerPassword(changePassword);
	}

	@PostMapping("/consumer/removeaccount")
	public String deleteConsumerByEmailId(@RequestBody Login login) throws UnsupportedEncodingException {
		return conServ.deleteConsumerDetailsByEmailId(login);
	}

	@GetMapping("/consumer/getallproducts")
	public Set<ProductDetails> getAllProductDetails() {
		return proServ.getAllProductDetails();
	}

	@GetMapping("/consumer/getsellersbyproductlocality")
	public Set<SellerDetails> getSellerDetailsByProductAndLocality(@RequestParam String emailId,
			@RequestParam String productName) {
		return sellServ.getSellersByProductAndLocality(emailId, productName);
	}

	@GetMapping("/consumer/getpurchaserecords")
	public List<PurchaseDetails> getPurchaseDetailsByConsumerEmailId(@RequestParam String emailId) {
		return purchaseServ.getPurchaseDetailsByConsumerEmailId(emailId);
	}

	@PostMapping("/consumer/placeconfirmorder")
	public PurchaseDetails purchaseConfirmOrderInRecord(@RequestBody ConfirmPurchaseOrder confirmPurchaseOrder) {
		return purchaseServ.insertPurchaseRecord(confirmPurchaseOrder);
	}

	// Sellers Controller

	@PostMapping("/seller/registerdetails")
	public SellerDetails registeredNewSeller(@Valid @RequestBody SellerDetails sellerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		return sellServ.registerNewSeller(sellerDetails);
	}

	@PostMapping("/seller/fetchdetailsbyemail")
	public SellerDetails getSellerDetailsByEmailIdandPassword(@RequestBody Login login)
			throws UnsupportedEncodingException {
		return sellServ.getSellerDetailsByEmailAndPassword(login.getEmailId(), login.getPassword());
	}

	@PostMapping("/seller/fetchdetailsbyusername")
	public SellerDetails getSellerDetailsByUsernameAndPassword(@RequestBody LoginByUsername loginByUsername) {
		return sellServ.getSellerDetailsByUsernameAndPassword(loginByUsername.getUsername(),
				loginByUsername.getPassword());
	}

	@PostMapping("/seller/fetchdetailsbyphonenumber")
	public SellerDetails getSellerDetailsByPhoneNumberAndPassword(@RequestBody LoginByPhone loginByPhone) {
		return sellServ.getSellerDetailsByPhoneNumberAndPassword(loginByPhone.getPhoneNumber(),
				loginByPhone.getPassword());
	}

	@PostMapping("/seller/updatedetails")
	public SellerDetails updateSellerDetails(@RequestBody SellerDetails sellerDetails) {
		return sellServ.updateSellerDetails(sellerDetails);
	}

	@PostMapping("/seller/changepassword")
	public String changeSellerPassword(@RequestBody ChangePassword changePassword) throws UnsupportedEncodingException {
		return sellServ.changeSellerPassword(changePassword);
	}

	@PostMapping("/seller/removeaccount")
	public String deleteSellerByEmailId(@RequestBody Login login) throws UnsupportedEncodingException {
		return sellServ.deleteSellerDetailsByEmailId(login);
	}

	@GetMapping("/seller/fetchnearbysellers")
	public Set<SellerDetails> getSellersByLocality(@RequestParam String emailId) {
		return sellServ.getSellerListByLocality(emailId);
	}

	@PostMapping("/seller/products/addproducts")
	public Set<ProductDetails> addProducts(@RequestBody SellerProducts sellerProducts) {
		return sellServ.addProducts(sellerProducts);
	}

	@GetMapping("/seller/products/getallproducts")
	public Set<ProductDetails> getSellerALlProductDetails(@RequestParam String emailId) {
		return sellServ.getSellerAllProductDetails(emailId);
	}

	@GetMapping("/seller/product/removeproduct")
	public String removeProductByProductName(@RequestParam String emailId, @RequestParam Integer pid) {
		return sellServ.removeProductFromList(emailId, pid);
	}

	@GetMapping("/seller/getpurchaserecords")
	public List<PurchaseDetails> getPurchaseDetailsBySellerEmailId(@RequestParam String emailId) {
		return purchaseServ.getPurchaseDetailsBySellerEmailId(emailId);
	}

	@GetMapping("/seller/changedeliverystatus")
	public void changeDeliveryStatus(@RequestParam int purchaseId, @RequestParam String status) {
		purchaseServ.changeDeliveryStatusBySeller(purchaseId, status);
	}

	@GetMapping()

	/*
	 * FeedBack and Queries
	 */

	@PostMapping("/feedback/submitfeedback")
	public String insertNewFeedBack(@Valid @RequestBody FeedBackDetails feedBackDetails) {
		return feedServ.insertNewFeedBackDetails(feedBackDetails);
	}

	@PostMapping("/consumer/query/submitquery")
	public String insertNewConsumerQuery(@Valid @RequestBody ConsumerQueryMessage consumerQuery) {
		return queryServ.insertNewConsumerQuery(consumerQuery);
	}

	@PostMapping("/seller/query/submitquery")
	public String insertNewSellerQuery(@Valid @RequestBody ConsumerQueryMessage sellerQuery) {
		return queryServ.insertNewSellerQuery(sellerQuery);
	}

	/*
	 * Administrator Controller
	 ***************************************************************************************
	 * 
	 * Consumer :
	 */

	@GetMapping("/admin/fetchconsumerbyemail")
	public ConsumerDetails getConsumerDetailsByEmailId(@RequestParam String emailId) {
		return conServ.getConsumerDetailsByEmailId(emailId);
	}

	@GetMapping("/admin/fetchconsumerbyusername")
	public ConsumerDetails getConsumerDetailsByUsername(@RequestParam String username) {
		return conServ.getConsumerDetailsByUsername(username);
	}

	@GetMapping("/admin/fetchconsumerbyphone")
	public ConsumerDetails getConsumerDetailsByPhoneNumber(@RequestParam String phoneNumber) {
		return conServ.getConsumerDetailsByPhoneNumer(phoneNumber);
	}

	@GetMapping("/admin/removeconsumeraccount")
	public String deleteConsumerById(@RequestParam Integer consumerId) {
		return conServ.deleteConsumerDetailsByConsumerId(consumerId);
	}

	@GetMapping("/admin/fetchallconsumers")
	public List<ConsumerDetails> getAllConsumers() {
		return conServ.getAllConsumerList();
	}

	@GetMapping("/admin/fetchconsumersbyname")
	public List<ConsumerDetails> getAllConsumersByName(String name) {
		return conServ.getConsumerDetailsByFirstName(name);
	}

	@GetMapping("/admin/fetchconsumersbypincode")
	public List<ConsumerDetails> getAllConsumersByPincode(@RequestParam String pincode) {
		return conServ.getConsumerListByPincode(pincode);
	}

	@GetMapping("/admin/fetchconsumersbydistrict")
	public List<ConsumerDetails> getAllConsumersByDistrict(String district) {
		return conServ.getConsumerListByDistrict(district);
	}

	@GetMapping("/admin/fetchconsumersbytown")
	public List<ConsumerDetails> getAllConsumersByTown(String town) {
		return conServ.getConsumerListByTown(town);
	}

	@GetMapping("/admin/getconsumerbyaid")
	public List<ConsumerDetails> getAllConsumerByAID(Integer aid) {
		return conServ.getConsumerByAid(aid);
	}

	@GetMapping("/admin/getdeletedconsumers")
	public List<DeletedConsumerRecords> getAllDeletedConsumers() {
		return delServ.getDeletedAllConsumerRecords();
	}

	@GetMapping("/admin/getdeletedconsumerbyemail")
	public DeletedConsumerRecords getDeletedConsumerRecordsByEmail(String emailId) {
		return delServ.getDeletedConsumerRecordByEmailId(emailId);
	}

	@GetMapping("/admin/getdeletedconsumerbyname")
	public List<DeletedConsumerRecords> getDeletedConsumerRecordsByName(String name) {
		return delServ.getDeletedConsumerRecordsByFirstName(name);
	}

	/*
	 * Seller :
	 */

	@GetMapping("/admin/fetchsellerbyemail")
	public SellerDetails getSellerDetailsByEmailId(@RequestParam String emailId) {
		return sellServ.getSellerDetailsByEmailId(emailId);
	}

	@GetMapping("/admin/fetchsellerbyusername")
	public SellerDetails getSellerDetailsByUsername(@RequestParam String username) {
		return sellServ.getSellerDetailsByUsername(username);
	}

	@GetMapping("/admin/fetchsellerbyphone")
	public SellerDetails getSellerDetailsByPhoneNumber(@RequestParam String phoneNumber) {
		return sellServ.getSellerDetailsByPhoneNumer(phoneNumber);
	}

	@GetMapping("/admin/removeselleraccount")
	public String deleteSellerById(@RequestParam Integer sellerId) {
		return sellServ.deleteSellerDetailsBySellerId(sellerId);
	}

	@GetMapping("/admin/fetchallsellers")
	public List<SellerDetails> getAllSellers() {
		return sellServ.getAllSellerList();
	}

	@GetMapping("/admin/fetchsellerbyname")
	public List<SellerDetails> getAllSellersByName(@RequestParam String name) {
		return sellServ.getSellerDetailsByFirstName(name);
	}

	@GetMapping("/admin/fetchsellersbypincode")
	public List<SellerDetails> getAllSellersByPincode(@RequestParam String pincode) {
		return sellServ.getSellerListByPincode(pincode);
	}

	@GetMapping("/admin/fetchsellersbydistrict")
	public List<SellerDetails> getAllSellersByDistrict(@RequestParam String district) {
		return sellServ.getSellerListByDistrict(district);
	}

	@GetMapping("/admin/fetchsellersbytown")
	public List<SellerDetails> getAllSellersByTown(@RequestParam String town) {
		return sellServ.getSellerListByTown(town);
	}

	@GetMapping("/admin/getdeletedsellers")
	public List<DeletedSellerRecords> getAllDeletedSellers() {
		return delServ.getDeletedAllSellerRecords();
	}

	@GetMapping("/admin/getdeletedsellerbyname")
	public List<DeletedSellerRecords> getAllDeletedSellerRecordsByName(@RequestParam String name) {
		return delServ.getDeletedSellerRecordsByFirstName(name);
	}

	@GetMapping("admin/getdeletedsellerbyemail")
	public DeletedSellerRecords getDeletedSellerRecordByEmailId(@RequestParam String emailId) {
		return delServ.getDeletedSellerRecordByEmailId(emailId);
	}

	/*
	 * Feedback and Query
	 */

	@GetMapping("/admin/feedback/id")
	public FeedBackDetails getFeedBackById(@RequestParam Integer fid) {
		return feedServ.getFeedBackDetailsById(fid);
	}

	@GetMapping("/admin/feedback/name")
	public List<FeedBackDetails> getFeedBackByName(@RequestParam String name) {
		return feedServ.getFeedBackDetailsByname(name);
	}

	@GetMapping("/admin/feedback/subject")
	public List<FeedBackDetails> getFeedBackBySubject(@RequestParam String subject) {
		return feedServ.getFeedBackDetailsBySubject(subject);
	}

	@GetMapping("/admin/consumer/query")
	public List<ConsumerQuery> getAllConsumerQueries() {
		return queryServ.getAllConsumerQueries();
	}

	@GetMapping("/admin/consumer/queriesbyemailid")
	public List<ConsumerQuery> getQueriesByConsumerEmailId(@RequestParam String emailId) {
		return queryServ.getConsumerQueriesByConsumerEmailId(emailId);
	}

	@GetMapping("/admin/consumer/queriesbydatetime")
	public List<ConsumerQuery> getConsumerQueriesByDateTime(@RequestParam String dateTime) {
		return queryServ.getConsumerQueriesByDateTime(dateTime);
	}

	@GetMapping("/admin/seller/query")
	public List<SellerQuery> getAllSellerQueries() {
		return queryServ.getAllSellerQueries();
	}

	@GetMapping("/admin/seller/queriesbyemailid")
	public List<SellerQuery> getQueriesBySellerEmailId(@RequestParam String emailId) {
		return queryServ.getSellerQueriesBySellerEmailId(emailId);
	}

	@GetMapping("/admin/seller/queriesbydatetime")
	public List<SellerQuery> getSellerQueriesByDateTime(@RequestParam String dateTime) {
		return queryServ.getSellerQueriesByDateTime(dateTime);
	}

	/*
	 * Products
	 */

	@PostMapping("/admin/products/addnewproduct")
	public String insertNewProductDetails(@RequestBody ProductDetails productDetails) {
		return proServ.insertNewProductDetails(productDetails);
	}

	@GetMapping("/admin/products/removeproduct")
	public String removeProductByPID(@RequestParam Integer pid) {
		return proServ.removeProductByPID(pid);
	}

	@GetMapping("/admin/products/getallpurchasedetails")
	public List<PurchaseDetails> getAllPurchaseDetails() {
		return purchaseServ.getAllPurchaseRecords();
	}

	@GetMapping("/admin/products/getpurchasedetailsbyid")
	public PurchaseDetails getPurchaseDetailsByPurchaseId(@RequestParam int purchaseId) {
		return purchaseServ.getPurchaseDetailsFromPurchaseId(purchaseId);
	}

	/*
	 * Administrator User
	 */

	@GetMapping("/admin/getadmindetails")
	public Administrator getAdminDetails() {
		return adminServ.getAdminDetails();
	}

	@PostMapping("/admin/login")
	public Administrator adminLogin(@RequestBody Login login) {
		return adminServ.getLoginDetails(login.getEmailId(), login.getPassword());
	}

	@PostMapping("/admin/changepassword")
	public String changeAdminPassword(@RequestBody ChangePassword changePassword) throws UnsupportedEncodingException {
		return adminServ.changeAdminPassword(changePassword);
	}

}
