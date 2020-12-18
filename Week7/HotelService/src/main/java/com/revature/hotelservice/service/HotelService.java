package com.revature.hotelservice.service;

import java.util.List;

import com.revature.hotelservice.pojo.Hotel;

public interface HotelService {
	
	public Hotel getHotelById(int id);
	
	public List<Hotel> getAllHotels();
	
	public Hotel makeHotel(Hotel hotel);
	
	public void removeHotel(int id);
	
	public void updateHotel(int id, Hotel hotel);

}
