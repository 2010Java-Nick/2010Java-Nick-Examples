package com.revature.guestservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.guestservice.pojo.Guest;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Integer>{

}
