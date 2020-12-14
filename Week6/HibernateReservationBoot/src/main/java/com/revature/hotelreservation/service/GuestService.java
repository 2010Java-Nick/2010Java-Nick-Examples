package com.revature.hotelreservation.service;

import java.util.List;

import com.revature.hotelreservation.pojo.Guest;

public interface GuestService {

	public Guest getGuestById(int id);
	
	public List<Guest> getGuestsByLastName(String lastName);
	
	public List<Guest> getAllGuests();
	
	public Guest createGuest(Guest guest);
	
	public Guest updateGuest(Guest guest, int guestId);
	
	public void deleteGuest(int guestId);
	
}
