package com.revature.hotelservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hotelservice.dao.HotelRepo;
import com.revature.hotelservice.pojo.Hotel;

@Service
public class HotelServiceImpl implements HotelService {
	
	private HotelRepo hotelRepo;
	
	@Autowired
	public void setHotelRepo(HotelRepo hotelRepo) {
		this.hotelRepo = hotelRepo;
	}

	@Override
	public Hotel getHotelById(int id) {
		return hotelRepo.getOne(id);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepo.findAll();
	}

	@Override
	public Hotel makeHotel(Hotel hotel) {
		return hotelRepo.save(hotel);
	}

	@Override
	public void removeHotel(int id) {
		Hotel hotel = hotelRepo.getOne(id);
		hotelRepo.delete(hotel);
	}

	@Override
	public void updateHotel(int id, Hotel hotel) {
		Hotel existingHotel = hotelRepo.getOne(id);
		if (existingHotel != null) {
			hotel.setHotelId(id);
			hotelRepo.save(hotel);
		}
		
	}

}
