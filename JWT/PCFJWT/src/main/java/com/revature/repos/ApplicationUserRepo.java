package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.ApplicationUser;
import com.revature.entities.Role;

@Repository
public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, Integer>{

	public ApplicationUser getByUsername(String name);
	

}
