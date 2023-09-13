package com.project.dairyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.ConsumerQuery;
import com.project.dairyproject.Entities.SellerQuery;

@Repository
public interface SellerQueryRepository extends CrudRepository<SellerQuery, Integer> {

	@Query("select q from SellerQuery q")
	public List<SellerQuery> findAllSellerQueries();

	@Query("select q from SellerQuery q where q.dateTime like %?1%")
	public List<SellerQuery> findSellerQueriesByDateTime(String dateTime);

	@Query("select q from SellerQuery q where q.sellerDetails.emailId like %?1%")
	public List<SellerQuery> findSellerQueriesBySellerEmailId(String emailId);

}
