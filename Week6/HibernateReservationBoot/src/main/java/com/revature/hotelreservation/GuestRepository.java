package com.revature.hotelreservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.hotelreservation.pojo.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

	public List<Guest> findGuestsByLastName(String lastName);
	
}
