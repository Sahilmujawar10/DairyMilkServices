package com.project.dairyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.ConsumerQuery;

@Repository
public interface ConsumerQueryRepository extends CrudRepository<ConsumerQuery, Integer> {

	@Query("select q from ConsumerQuery q")
	public List<ConsumerQuery> findAllConsumerQueries();

	@Query("select q from ConsumerQuery q where q.dateTime like %?1%")
	public List<ConsumerQuery> findConsumerQueriesByDateTime(String dateTime);

	@Query("select q from ConsumerQuery q where q.consumerDetails.emailId like %?1%")
	public List<ConsumerQuery> findConsumerQueriesByConsumerEmailId(String emailId);

}
