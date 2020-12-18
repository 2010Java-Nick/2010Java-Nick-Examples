package com.revature.guestservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.guestservice.pojo.Guest;
import com.revature.guestservice.service.GuestService;

@RestController(value = "/guest")
public class GuestController {

	private GuestService guestService;

	@Autowired
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}


	@GetMapping(path = "/{guestId}")
	public Guest getGuest(@PathVariable(name = "guestId")int guestId) {
		return guestService.getGuestById(guestId);
	}
	
	@GetMapping
	public List<Guest> getAllGuests(){
		return guestService.getAllGuests();
	}
	
	@PostMapping
	public Guest createGuest(@RequestBody Guest guest) {
		return guestService.makeGuest(guest);
	}
	
	@DeleteMapping("/{guestId}")
	public String deleteGuest(@PathVariable("guestId")int guestId) {
		guestService.removeGuest(guestId);
		return "Guest successfully deleted";
	}
	
	@PutMapping("/{guestId}")
	public String updateGuest(@PathVariable("guestId")int guestId, @RequestBody Guest guest) {
		guestService.updateGuest(guestId, guest);
		return "Guest successfully updated";
	}
	
}
