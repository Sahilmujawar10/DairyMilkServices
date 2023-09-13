package com.project.dairyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.ConsumerDetails;

@Repository
public interface ConsumerRepository extends CrudRepository<ConsumerDetails, Integer> {

	@Query("select c from ConsumerDetails c where c.username = ?1 and c.password = ?2")
	public ConsumerDetails findConsumerDetailsByUsernameAndPassword(String username, String password);

	@Query("select c from ConsumerDetails c where c.emailId = ?1 and c.password = ?2")
	public ConsumerDetails findConsumerDetailsByEmailAndPassword(String emailId, String password);

	@Query("select c from ConsumerDetails c where c.phoneNumber = ?1 and c.password = ?2")
	public ConsumerDetails findConsumerDetailsByPhoneNumberAndPassword(String phoneNumber, String password);

	@Query("select count(c) from ConsumerDetails c where c.emailId = ?1")
	public int findConsumerDetailsByEmailId(String emailId);

	@Query("select count(c) from ConsumerDetails c where c.username = ?1")
	public int findConsumerDetailsByUsername(String username);

	@Query("select count(c) from ConsumerDetails c where c.phoneNumber = ?1")
	public int findConsumerDetailsByPhoneNumber(String phoneNumber);

	@Query("select c from ConsumerDetails c where c.emailId = ?1")
	public ConsumerDetails findConsumerDetailsByEmailIdOnly(String emailId);

	@Query("select c from ConsumerDetails c where c.username = ?1")
	public ConsumerDetails findConsumerDetailsByUsernameOnly(String username);

	@Query("select c from ConsumerDetails c where c.phoneNumber = ?1")
	public ConsumerDetails findConsumerDetailsByPhoneNumberOnly(String phoneNumber);

	public int deleteConsumerDetailsByConsumerId(Integer consumerid);

	public int deleteConsumerDetailsByEmailId(String emailId);

	@Query("select c from ConsumerDetails c")
	public List<ConsumerDetails> findAllConsumerDetails();

	@Query("select c from ConsumerDetails c where firstName like %?1%")
	public List<ConsumerDetails> findConsumerByName(String name);

	@Query("select c from ConsumerDetails c where c.address.pincode = ?1")
	public List<ConsumerDetails> findConsumersByPincode(String pincode);

	@Query("select c from ConsumerDetails c where c.address.district = ?1")
	public List<ConsumerDetails> findConsumersByDistrict(String district);

	@Query("select c from ConsumerDetails c where c.address.town = ?1")
	public List<ConsumerDetails> findConsumersByTown(String town);

	@Query("select c from ConsumerDetails c where c.address.AID = ?1")
	public List<ConsumerDetails> findConsumerDetailsByAid(Integer aid);

	@Query("select c from ConsumerDetails c where c.consumerId = ?1")
	public ConsumerDetails findConsumerDetailsByConsumerId(Integer consumerId);

}
