package com.revature.hotelreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hotelreservation.GuestRepository;
import com.revature.hotelreservation.pojo.Guest;

@Service
public class GuestServiceBoot implements GuestService {
	
	private GuestRepository guestRepo;
	
	@Autowired
	public void setGuestRepo(GuestRepository guestRepo) {
		this.guestRepo = guestRepo;
	}

	@Override
	public Guest getGuestById(int id) {
		return guestRepo.getOne(id);
	}
	
	public List<Guest> getGuestsByLastName(String lastName) {
		return guestRepo.findGuestsByLastName(lastName);
	}

	@Override
	public List<Guest> getAllGuests() {
		// TODO Auto-generated method stub
		return guestRepo.findAll();
	}

	@Override
	public Guest createGuest(Guest guest) {
		return guest;
	}

	@Override
	public Guest updateGuest(Guest guest, int guestId) {
		Guest newGuest = guestRepo.save(guest);
		return newGuest;
	}

	@Override
	public void deleteGuest(int guestId) {
		// TODO Auto-generated method stub

	}

}
