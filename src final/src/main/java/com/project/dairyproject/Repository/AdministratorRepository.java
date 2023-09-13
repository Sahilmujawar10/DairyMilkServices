package com.project.dairyproject.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dairyproject.Entities.Administrator;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.adminId = 1")
	public Administrator findAdminDetails();

	@Query("select a from Administrator a where a.emailId = ?1 and a.password = ?2")
	public Administrator findAdminDetailsByEmailIdAndPassword(String emailId, String password);
}
