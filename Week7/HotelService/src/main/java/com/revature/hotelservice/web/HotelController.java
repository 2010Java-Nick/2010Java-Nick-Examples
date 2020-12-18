package com.revature.hotelservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hotelservice.pojo.Hotel;
import com.revature.hotelservice.service.HotelService;

@RestController
public class HotelController {
	
	private HotelService hotelService;

	@Autowired
	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	@GetMapping("/hotel/{hotelId}")
	public Hotel getHotel(@PathVariable(name = "hotelId") int hotelId) {
		return hotelService.getHotelById(hotelId);
	}
	
	@GetMapping("/hotel")
	public List<Hotel> getAllHotels() {
		return hotelService.getAllHotels();
	}
	
	@PostMapping("/hotel")
	public Hotel createHotel(@RequestBody Hotel hotel) {
		return hotelService.makeHotel(hotel);
	}
	
	@DeleteMapping("/hotel/{hotelId}")
	public String deleteHotel(@PathVariable("hotelId")int hotelId) {
		hotelService.removeHotel(hotelId);
		return "Hotel successfully deleted";
	}
	
	@PutMapping("/hotel/{hotelId}")
	public String updateHotel(@PathVariable("hotelId")int hotelId, @RequestBody Hotel hotel) {
		hotelService.updateHotel(hotelId, hotel);
		return "Hotel successfully updated";
	}

}
