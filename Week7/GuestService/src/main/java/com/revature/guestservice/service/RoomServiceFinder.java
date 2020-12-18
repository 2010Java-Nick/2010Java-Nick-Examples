package com.revature.guestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.guestservice.dto.RoomDto;

@Service
public class RoomServiceFinder implements RoomService {

	private static final String ROOM_URL = "http://localhost:10001/room/";
	
	
	private RestTemplate restTemplate;


	@Override
	public RoomDto getRoomById(int roomId) {
		
		restTemplate = new RestTemplateBuilder().build();
		
		return restTemplate.getForObject(ROOM_URL + roomId, RoomDto.class);
		
	}

}
