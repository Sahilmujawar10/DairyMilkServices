package com.project.dairyproject.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.dairyproject.Entities.DeletedSellerRecords;

@Repository
public interface DeletedSellerRepository extends CrudRepository<DeletedSellerRecords, Integer> {

	@Query("select s from DeletedSellerRecords s")
	public List<DeletedSellerRecords> findAllDeletedSellerRecords();

	@Query("select s from DeletedSellerRecords s where s.firstName like %?1%")
	public List<DeletedSellerRecords> findDeletedSellerRecordsByFirstName(String firstName);

	@Query("select s from DeletedSellerRecords s where s.emailId like %?1%")
	public DeletedSellerRecords findDeletedSellerRecordsByEmailId(String emailId);

}
