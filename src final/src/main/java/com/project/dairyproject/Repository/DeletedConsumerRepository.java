package com.project.dairyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.DeletedConsumerRecords;

@Repository
public interface DeletedConsumerRepository extends CrudRepository<DeletedConsumerRecords, Integer> {

	@Query("select c from DeletedConsumerRecords c")
	public List<DeletedConsumerRecords> findAllDeletedConsumerRecords();

	@Query("select c from DeletedConsumerRecords c where c.firstName like %?1%")
	public List<DeletedConsumerRecords> findDeletedConsumerRecordsByFirstName(String firstName);

	@Query("select c from DeletedConsumerRecords c where c.emailId like %?1%")
	public DeletedConsumerRecords findDeletedConsumerByEmailId(String emailId);

}
