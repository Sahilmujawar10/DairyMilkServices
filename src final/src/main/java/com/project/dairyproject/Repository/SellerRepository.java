package com.project.dairyproject.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.dairyproject.Entities.ProductDetails;
import com.project.dairyproject.Entities.SellerDetails;

public interface SellerRepository extends CrudRepository<SellerDetails, Integer> {

	@Query("select s from SellerDetails s where s.username = ?1 and s.password = ?2")
	public SellerDetails findSellerDetailsByUsernameAndPassword(String username, String password);

	@Query("select s from SellerDetails s where s.emailId = ?1 and s.password = ?2")
	public SellerDetails findSellerDetailsByEmailAndPassword(String emailId, String password);

	@Query("select s from SellerDetails s where s.phoneNumber = ?1 and s.password = ?2")
	public SellerDetails findSellerDetailsByPhoneNumberAndPassword(String phoneNumber, String password);

	@Query("select count(s) from SellerDetails s where s.emailId = ?1")
	public int findSellerDetailsByEmailId(String emailId);

	@Query("select count(s) from SellerDetails s where s.username = ?1")
	public int findSellerDetailsByUsername(String username);

	@Query("select count(s) from SellerDetails s where s.phoneNumber = ?1")
	public int findSellerDetailsByPhoneNumber(String phoneNumber);

	@Query("select s from SellerDetails s where s.emailId like %?1%")
	public SellerDetails findSellerDetailsByEmailIdOnly(String emailId);

	@Query("select s from SellerDetails s where s.username = ?1")
	public SellerDetails findSellerDetailsByUsernameOnly(String username);

	@Query("select s from SellerDetails s where s.phoneNumber = ?1")
	public SellerDetails findSellerDetailsByPhoneNumberOnly(String phoneNumber);

	public int deleteSellerDetailsBySellerId(Integer sellerId);

	public int deleteSellerDetailsByEmailId(String emailId);

	@Query("select s from SellerDetails s")
	public List<SellerDetails> findAllSellerDetails();

	@Query("select s from SellerDetails s where firstName like %?1%")
	public List<SellerDetails> findSellerByName(String name);

	@Query("select s from SellerDetails s where s.address.pincode = ?1")
	public List<SellerDetails> findSellersByPincode(String pincode);

	@Query("select s from SellerDetails s where s.address.district = ?1")
	public List<SellerDetails> findSellersByDistrict(String district);

	@Query("select s from SellerDetails s where s.address.town = ?1")
	public List<SellerDetails> findSellersByTown(String town);

	@Query("select s from SellerDetails s where s.sellerId = ?1")
	public SellerDetails findSellerDetailsBySellerId(Integer sellerId);

}
