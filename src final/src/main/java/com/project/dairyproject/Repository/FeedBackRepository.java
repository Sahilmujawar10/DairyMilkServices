package com.project.dairyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.dairyproject.Entities.FeedBackDetails;

public interface FeedBackRepository extends CrudRepository<FeedBackDetails, Integer> {

	@Query("select f from FeedBackDetails f")
	public List<FeedBackDetails> findAllFeedBackDetails();

	@Query("select f from FeedBackDetails f where f.name like %?1%")
	public List<FeedBackDetails> findFeedBackDetailsByName(String name);

	@Query("select f from FeedBackDetails f where f.subject like %?1%")
	public List<FeedBackDetails> findFeedBackDetailsBySubject(String subject);

	@Query("select f from FeedBackDetails f where f.FID = ?1")
	public FeedBackDetails findFeedBackDetailsById(Integer fid);

}
