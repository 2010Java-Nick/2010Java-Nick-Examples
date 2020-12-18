package com.revature.guestservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.guestservice.pojo.Guest;
import com.revature.guestservice.repo.GuestRepo;

@Service
public class GuestServiceImpl implements GuestService {

	private GuestRepo guestRepo;
	
	@Autowired
	public void setGuestRepo(GuestRepo guestRepo) {
		this.guestRepo = guestRepo;
	}

	@Override
	public Guest getGuestById(int id) {
		return guestRepo.getOne(id);
	}

	@Override
	public List<Guest> getAllGuests() {
		return guestRepo.findAll();
	}

	@Override
	public Guest makeGuest(Guest guest) {
		return guestRepo.save(guest);
	}

	@Override
	public void removeGuest(int id) {
		Guest guest = guestRepo.getOne(id);
		guestRepo.delete(guest);
	}

	@Override
	public void updateGuest(int id, Guest guest) {
		Guest existingGuest = guestRepo.getOne(id);
		if (existingGuest != null) {
			guest.setGuestId(id);
			guestRepo.save(guest);
		}
	}

}
