package com.revature.hotelreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hotelreservation.pojo.Guest;
import com.revature.hotelreservation.service.GuestService;

@RestController
public class GuestController {
	
	private GuestService guestService;
	
	@Autowired
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}
	
	@GetMapping("/guest")
	public List<Guest> getAllGuests() {
		return guestService.getAllGuests();
	}
	
	@GetMapping("/guest/lastName/{lastName}")
	public List<Guest> getGuestsByLastName(@PathVariable("lastName") String lastName) {
		return guestService.getGuestsByLastName(lastName);
	}



	@PutMapping("/guest/{id}")
	public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest, @PathVariable("id") int guestId) {
		
		Guest newGuest = guestService.updateGuest(guest, guestId);
		
		return new ResponseEntity<Guest>(newGuest, HttpStatus.OK);
		
	}
	
}
