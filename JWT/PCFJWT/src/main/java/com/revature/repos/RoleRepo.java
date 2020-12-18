package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	public Role getByName(String name);
	

}
