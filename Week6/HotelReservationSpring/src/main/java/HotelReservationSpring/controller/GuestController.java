package HotelReservationSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.service.GuestService;

@Controller
public class GuestController {

	GuestService guestService;
	
	@Autowired
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}

	public void createGuest() {

	}

	@RequestMapping(path = "/guest/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Guest getGuest(@PathVariable(name = "id")int id) {
		return guestService.getGuestById(id);
	}

	@RequestMapping(path = "/guest", method = RequestMethod.GET)
	@ResponseBody
	public List<Guest> getAllGuests() {

		return guestService.getAllGuests();
		
	}

	public void updateGuest() {
		

	}

}
