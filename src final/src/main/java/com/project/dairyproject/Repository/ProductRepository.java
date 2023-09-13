package com.project.dairyproject.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.ProductDetails;

@Repository
public interface ProductRepository extends CrudRepository<ProductDetails, Integer> {

	@Query("select p from ProductDetails p")
	public Set<ProductDetails> findAllProductDetails();

	@Query("select p from ProductDetails p where p.name = ?1")
	public ProductDetails findProductDetailsByName(String name);

	@Query("select p from ProductDetails p where p.PID = ?1")
	public ProductDetails findProductDetailByPid(Integer PID);

	@Modifying
	@Query("delete from ProductDetails where PID = ?1")
	public int deleteProductDetailsByPID(Integer pid);

}
