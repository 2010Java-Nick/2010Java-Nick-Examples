package com.revature.guestservice.service;

import java.util.List;

import com.revature.guestservice.dto.GuestDto;
import com.revature.guestservice.pojo.Guest;

public interface GuestService {
	
	public GuestDto getGuestById(int id);
	
	public List<Guest> getAllGuests();
	
	public Guest makeGuest(Guest guest);
	
	public void removeGuest(int id);
	
	public void updateGuest(int id, Guest guest);

}
