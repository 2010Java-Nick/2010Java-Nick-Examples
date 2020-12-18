package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Privilage;
import com.revature.entities.Role;

@Repository
public interface PrivilageRepo extends JpaRepository<Privilage, Integer>{

	public Privilage getByName(String name);
	

}
