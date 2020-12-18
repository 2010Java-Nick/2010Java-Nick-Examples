package com.revature.guestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
	
	private int roomId;
	
	private int beds;
	
	private boolean smoking;
	
	private boolean roomService;
	
	private int roomNumber;
	
	private int hotelId;
}
