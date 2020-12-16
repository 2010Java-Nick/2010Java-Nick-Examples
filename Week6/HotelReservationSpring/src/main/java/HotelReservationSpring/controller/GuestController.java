package HotelReservationSpring.controller;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import HotelReservationSpring.dto.GuestDto;
import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.service.GuestService;

@Controller
public class GuestController implements ApplicationContextAware {

	GuestService guestService;
	
	@Autowired
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}

	@RequestMapping(path = "/guest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<GuestDto> createGuest(@RequestBody GuestDto guest) {

		Guest newGuest = guestService.createGuest(guest.toGuest());
		
		ResponseEntity<GuestDto> re = new ResponseEntity<GuestDto>(new GuestDto(newGuest), HttpStatus.CREATED);
		
		return re;
		
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("----------------------------Working-----------------------------------");
	}

}
